package com.fastcode.timesheetapp1.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheetapp1.restcontrollers.core.UsersController;
import com.fastcode.timesheetapp1.addons.scheduler.application.job.IJobAppService;
import com.fastcode.timesheetapp1.addons.scheduler.application.job.dto.CreateJobInput;
import com.fastcode.timesheetapp1.addons.scheduler.application.trigger.ITriggerAppService;
import com.fastcode.timesheetapp1.addons.scheduler.application.trigger.dto.CreateTriggerInput;
import com.fastcode.timesheetapp1.addons.scheduler.application.trigger.dto.EmailTriggerInfo;
import com.fastcode.timesheetapp1.application.extended.authorization.users.IUsersAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.timesheet.ITimesheetAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.authorization.userspermission.IUserspermissionAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.authorization.usersrole.IUsersroleAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.usertask.IUsertaskAppServiceExtended;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.fastcode.timesheetapp1.security.JWTAppService;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;
import com.fastcode.timesheetapp1.domain.core.authorization.users.UsersEntity;

@RestController
@RequestMapping("/users/extended")
public class UsersControllerExtended extends UsersController {

	public UsersControllerExtended(IUsersAppServiceExtended usersAppServiceExtended, ITimesheetAppServiceExtended timesheetAppServiceExtended, IUserspermissionAppServiceExtended userspermissionAppServiceExtended, IUsersroleAppServiceExtended usersroleAppServiceExtended, IUsertaskAppServiceExtended usertaskAppServiceExtended,
			PasswordEncoder pEncoder,JWTAppService jwtAppService, LoggingHelper helper, Environment env, IJobAppService jobApp, ITriggerAppService triggerApp) {
		super(
				usersAppServiceExtended,
				timesheetAppServiceExtended,
				userspermissionAppServiceExtended,
				usersroleAppServiceExtended,
				usertaskAppServiceExtended,
				pEncoder,
				jwtAppService,
				helper, env);

		this.jobApp = jobApp;
		this.triggerApp = triggerApp;
		this.usersAppServiceExtended = usersAppServiceExtended;
	}

	@NonNull protected final IJobAppService jobApp;

	@NonNull protected final ITriggerAppService triggerApp;

	@Qualifier("usersAppServiceExtended")
	@NonNull protected final IUsersAppServiceExtended usersAppServiceExtended;

	//Add your custom code here

	//@PreAuthorize("hasAnyAuthority('USERSENTITY_CREATE')")
	@RequestMapping(value= "/reminder",method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<Map<String,String>> createTriggerAndSendEmail(@RequestBody @Valid EmailTriggerInfo info) throws ClassNotFoundException, Exception {

		UsersEntity loggedInUser = usersAppServiceExtended.getUsers();

		if(jobApp.returnJob("emailJob", "emailGroup") == null ) {
			CreateJobInput jobInput = new CreateJobInput();
			jobInput.setIsDurable(true);
			jobInput.setJobClass("com.fastcode.timesheetapp1.addons.scheduler.samplejobs.emailJob");
			jobInput.setJobName("emailJob");
			jobInput.setJobGroup("emailJobGroup");
			jobInput.setJobDescription("Reminder Email Job");

			jobApp.createJob(jobInput);

		}

		CreateTriggerInput triggerInput = new CreateTriggerInput();
		String cronExpression = "0 " + info.getTime().getMinute() + " " + info.getTime().getHour() + " ? * " + info.getDays() +" *";

		// 0 30 4 ? * MON,TUE,WED,THU,FRI *
		triggerInput.setCronExpression(cronExpression);
		triggerInput.setJobName("emailJob");
		triggerInput.setJobGroup("emailJobGroup");
		triggerInput.setJobClass("com.fastcode.timesheetapp1.addons.scheduler.samplejobs.emailJob");
		//triggerInput.setStartTime(date);
		triggerInput.setTriggerName(loggedInUser.getUsername() + "trigger10");
		triggerInput.setTriggerGroup("emailTriggerGroup10");
		triggerInput.setTriggerType("cron");

		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("email", loggedInUser.getEmailaddress());
		triggerInput.setTriggerMapData(mapData);

		Boolean status = triggerApp.createTrigger(triggerInput);

		Map<String,String> output = new HashMap<String, String>();
		if(status) {
			usersAppServiceExtended.associateTriggerWithUser(loggedInUser, triggerInput);
			output.put("status", "true");
			output.put("msg", "trigger successfully associated");
		}
		else {
			output.put("status", "false");
			output.put("msg", "trigger already exists");
		}
		return new ResponseEntity(output, HttpStatus.OK);
	}	

}


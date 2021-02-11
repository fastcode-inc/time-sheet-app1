package com.fastcode.timesheetapp1.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheetapp1.restcontrollers.core.UsersController;
import com.fastcode.timesheetapp1.addons.scheduler.application.job.IJobAppService;
import com.fastcode.timesheetapp1.addons.scheduler.application.job.JobAppService;
import com.fastcode.timesheetapp1.addons.scheduler.application.job.dto.CreateJobInput;
import com.fastcode.timesheetapp1.addons.scheduler.application.trigger.ITriggerAppService;
import com.fastcode.timesheetapp1.addons.scheduler.application.trigger.dto.CreateTriggerInput;
import com.fastcode.timesheetapp1.application.extended.authorization.users.IUsersAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.timesheet.ITimesheetAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.authorization.userspermission.IUserspermissionAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.authorization.usersrole.IUsersroleAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.usertask.IUsertaskAppServiceExtended;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.fastcode.timesheetapp1.security.JWTAppService;

import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
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
	public ResponseEntity<Map<String,String>> createTriggerAndSendEmail(@RequestBody @Valid List<String> days, @RequestParam("time") String time) throws ClassNotFoundException, Exception {

		UsersEntity loggedInUser = usersAppServiceExtended.getUsers();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime timef = LocalTime.parse(time,formatter);
		
		if(jobApp.returnJob("emailJob", "emailGroup") == null ) {
		CreateJobInput jobInput = new CreateJobInput();
		jobInput.setIsDurable(true);
		jobInput.setJobClass("com.fastcode.timesheetapp1.addons.scheduler.samplejobs.emailJob");
		jobInput.setJobName("emailJob");
		jobInput.setJobGroup("emailJobGroup");
		jobInput.setJobDescription("Reminder Email Job");
		
		jobApp.createJob(jobInput);
		
		}
		//		FindUsersByUsernameOutput foundUsers = _usersAppService.findByUsername(users.getUsername());
		CreateTriggerInput triggerInput = new CreateTriggerInput();
		// 0 30 4 ? * MON,TUE,WED,THU,FRI *
		triggerInput.setCronExpression("0/30 0/1 * 1/1 * ? *");
		triggerInput.setJobName("emailJob");
		triggerInput.setJobGroup("emailJobGroup");
		triggerInput.setJobClass("com.fastcode.timesheetapp1.addons.scheduler.samplejobs.emailJob");
		//triggerInput.setStartTime(date);
		triggerInput.setTriggerName(loggedInUser.getUsername() + "trigger11");
		triggerInput.setTriggerGroup("emailTriggerGroup11");
		triggerInput.setTriggerType("cron");
		
		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("email", loggedInUser.getEmailaddress());
		triggerInput.setTriggerMapData(mapData);
		
		triggerApp.createTrigger(triggerInput);
		
		Map<String,String> output = new HashMap<String, String>();
		
		return new ResponseEntity(output, HttpStatus.OK);
	}	

}


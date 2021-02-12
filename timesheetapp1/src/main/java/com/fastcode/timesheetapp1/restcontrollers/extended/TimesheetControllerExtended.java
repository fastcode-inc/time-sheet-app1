package com.fastcode.timesheetapp1.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheetapp1.restcontrollers.core.TimesheetController;
import com.fastcode.timesheetapp1.security.SecurityConstants;

import lombok.NonNull;

import com.fastcode.timesheetapp1.application.extended.timesheet.ITimesheetAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.timesheet.dto.TimesheetOutput;
import com.fastcode.timesheetapp1.application.extended.timesheetdetails.ITimesheetdetailsAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.timesheetdetails.dto.TimesheetdetailsInput;
import com.fastcode.timesheetapp1.application.extended.timesheetdetails.dto.TimesheetdetailsOutput;
import com.fastcode.timesheetapp1.application.extended.timesheetstatus.ITimesheetstatusAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.authorization.users.IUsersAppServiceExtended;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;
import com.fastcode.timesheetapp1.domain.core.authorization.users.UsersEntity;

@RestController
@RequestMapping("/timesheet/extended")
public class TimesheetControllerExtended extends TimesheetController {

	public TimesheetControllerExtended(ITimesheetAppServiceExtended timesheetAppServiceExtended, ITimesheetdetailsAppServiceExtended timesheetdetailsAppServiceExtended,
			ITimesheetstatusAppServiceExtended timesheetstatusAppServiceExtended, IUsersAppServiceExtended usersAppServiceExtended,
			LoggingHelper helper, Environment env) {
		super(
				timesheetAppServiceExtended,
				timesheetdetailsAppServiceExtended,
				timesheetstatusAppServiceExtended,
				usersAppServiceExtended,
				helper, env);
		this._timesheetdetailsAppServiceExtended = timesheetdetailsAppServiceExtended;
		this._timesheetAppServiceExtended = timesheetAppServiceExtended;
		this.usersAppServiceExtended = usersAppServiceExtended;
	}

	//Add your custom code here

	@Qualifier("timesheetdetailsExtendedAppService")
	@NonNull  protected final ITimesheetdetailsAppServiceExtended  _timesheetdetailsAppServiceExtended;

	@Qualifier("timesheetExtendedAppService")
	@NonNull  protected final ITimesheetAppServiceExtended  _timesheetAppServiceExtended;

	@Qualifier("usersAppServiceExtended")
	@NonNull protected final IUsersAppServiceExtended usersAppServiceExtended;
	
	
	@RequestMapping(value="/timesheetdetails", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<Map<String,String>> createMultipleDetails(@RequestBody @Valid List<TimesheetdetailsInput> inputList) {

		return new ResponseEntity(_timesheetdetailsAppServiceExtended.createMultipleDetails(inputList), HttpStatus.OK);
	}
	
	//@PreAuthorize("hasAnyAuthority('TIMESHEETENTITY_READ')")
	@RequestMapping(value = "/timesheetdetails", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<List<TimesheetdetailsOutput>> findTimesheetdetailsByWorkDate(@RequestParam("workDate") String workDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(workDate,formatter);
		List<TimesheetdetailsOutput> output = _timesheetdetailsAppServiceExtended.findByWorkDate(date);
		Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Not found")));

		return new ResponseEntity(output, HttpStatus.OK);
	}

	//@PreAuthorize("hasAnyAuthority('TIMESHEETENTITY_READ')")
	@RequestMapping(value = "/getTimesheet", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<TimesheetOutput> findTimesheetByDate(@RequestParam("date") String date, @RequestParam("includeDetails") Boolean includeDetails, @RequestParam(value="userId", required=false) Long userId,HttpServletRequest request) throws Exception {

		if(userId !=null) {
		String token = request.getHeader("Authorization");
		if(token !=null && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
		}
		
		if(!usersAppServiceExtended.parseTokenAndCheckIfPermissionExists(token, "TIMESHEETENTITY_READ")) {
			throw new Exception("You don't have permission to fetch timesheet details against userid " + userId);
		}
		}
		else
		{
		UsersEntity loggedInUser = usersAppServiceExtended.getUsers();
		userId = loggedInUser.getId();
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate datel = LocalDate.parse(date,formatter);
		TimesheetOutput output = _timesheetAppServiceExtended.findTimesheetByDate(datel, includeDetails, userId);
		Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Not found")));

		return new ResponseEntity(output, HttpStatus.OK);
	}
}


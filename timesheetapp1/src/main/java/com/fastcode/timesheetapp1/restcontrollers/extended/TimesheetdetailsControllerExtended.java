package com.fastcode.timesheetapp1.restcontrollers.extended;

import com.fastcode.timesheetapp1.application.extended.task.ITaskAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.timeofftype.ITimeofftypeAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.timesheet.ITimesheetAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.timesheetdetails.ITimesheetdetailsAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.timesheetdetails.dto.TimesheetdetailsInput;
import com.fastcode.timesheetapp1.application.extended.timesheetdetails.dto.TimesheetdetailsOutput;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;
import com.fastcode.timesheetapp1.restcontrollers.core.TimesheetdetailsController;

import lombok.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timesheetdetails/extended")
public class TimesheetdetailsControllerExtended extends TimesheetdetailsController {

		public TimesheetdetailsControllerExtended(ITimesheetdetailsAppServiceExtended timesheetdetailsAppServiceExtended, ITaskAppServiceExtended taskAppServiceExtended, ITimeofftypeAppServiceExtended timeofftypeAppServiceExtended, ITimesheetAppServiceExtended timesheetAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		timesheetdetailsAppServiceExtended,
    	taskAppServiceExtended,
    	timeofftypeAppServiceExtended,
    	timesheetAppServiceExtended,
		helper, env);
		this._timesheetdetailsAppServiceExtended = timesheetdetailsAppServiceExtended; 
	}

	//Add your custom code here
	@Qualifier("timesheetdetailsExtendedAppService")
	@NonNull  protected final ITimesheetdetailsAppServiceExtended  _timesheetdetailsAppServiceExtended;

	@PreAuthorize("hasAnyAuthority('CREATE_TIMESHEETDETAILS_BULK')")
	@RequestMapping(value="/timesheetdetails", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<Map<String,String>> createMultipleDetails(@RequestBody @Valid List<TimesheetdetailsInput> inputList) {

		return new ResponseEntity(_timesheetdetailsAppServiceExtended.createMultipleDetails(inputList), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('FIND_BY_DATE_TIMESHEETDETAILS')")
	@RequestMapping(value = "/timesheetdetails", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<List<TimesheetdetailsOutput>> findTimesheetdetailsByWorkDate(@RequestParam("workDate") String workDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(workDate,formatter);
		List<TimesheetdetailsOutput> output = _timesheetdetailsAppServiceExtended.findByWorkDate(date);

		return new ResponseEntity(output, HttpStatus.OK);
	}
}


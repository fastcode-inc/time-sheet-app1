package com.fastcode.timesheetapp1.application.extended.timesheet.dto;

import com.fastcode.timesheetapp1.application.core.timesheet.dto.FindTimesheetByIdOutput;
import com.fastcode.timesheetapp1.application.extended.timesheetdetails.dto.TimesheetdetailsOutput;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class TimesheetOutput extends FindTimesheetByIdOutput {

	List<TimesheetdetailsOutput> timesheetdetailsList;
	BigDecimal hours;
}

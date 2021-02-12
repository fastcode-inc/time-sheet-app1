package com.fastcode.timesheetapp1.application.extended.timesheet;

import java.time.LocalDate;

import com.fastcode.timesheetapp1.application.core.timesheet.ITimesheetAppService;
import com.fastcode.timesheetapp1.application.core.timesheet.dto.UpdateTimesheetOutput;
import com.fastcode.timesheetapp1.application.extended.timesheet.dto.TimesheetOutput;

public interface ITimesheetAppServiceExtended extends ITimesheetAppService {

	//Add your custom code here
	public TimesheetOutput findTimesheetByDate(LocalDate date, Boolean includeDetails, Long userId);

	public UpdateTimesheetOutput updateTimesheetStatus(Long timesheetId, String status);
}

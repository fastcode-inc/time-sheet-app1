package com.fastcode.timesheetapp1.application.extended.timesheet;

import com.fastcode.timesheetapp1.application.core.timesheet.ITimesheetAppService;
import com.fastcode.timesheetapp1.application.core.timesheet.dto.UpdateTimesheetOutput;
import com.fastcode.timesheetapp1.application.extended.timesheet.dto.TimesheetOutput;
import com.fastcode.timesheetapp1.application.extended.timesheet.dto.UpdateStatus;

import java.time.LocalDate;

public interface ITimesheetAppServiceExtended extends ITimesheetAppService {

	//Add your custom code here
	public TimesheetOutput findTimesheetByDate(LocalDate date, Boolean includeDetails, Long userId);

	public UpdateTimesheetOutput updateTimesheetStatus(Long timesheetId, UpdateStatus input);
	
//	public List<TimesheetOutput> findWithHours(SearchCriteria search, Pageable pageable) throws Exception;
}

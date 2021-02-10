package com.fastcode.timesheetapp1.application.extended.timesheet;

import org.springframework.stereotype.Service;
import com.fastcode.timesheetapp1.application.core.timesheet.TimesheetAppService;

import com.fastcode.timesheetapp1.domain.extended.timesheet.ITimesheetRepositoryExtended;
import com.fastcode.timesheetapp1.domain.extended.timesheetstatus.ITimesheetstatusRepositoryExtended;
import com.fastcode.timesheetapp1.domain.extended.authorization.users.IUsersRepositoryExtended;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;

@Service("timesheetAppServiceExtended")
public class TimesheetAppServiceExtended extends TimesheetAppService implements ITimesheetAppServiceExtended {

	public TimesheetAppServiceExtended(ITimesheetRepositoryExtended timesheetRepositoryExtended,
				ITimesheetstatusRepositoryExtended timesheetstatusRepositoryExtended,IUsersRepositoryExtended usersRepositoryExtended,ITimesheetMapperExtended mapper,LoggingHelper logHelper) {

		super(timesheetRepositoryExtended,
		timesheetstatusRepositoryExtended,usersRepositoryExtended,mapper,logHelper);

	}

 	//Add your custom code here
 
}


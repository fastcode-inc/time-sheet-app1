package com.fastcode.timesheetapp1.application.extended.timesheetstatus;

import org.springframework.stereotype.Service;
import com.fastcode.timesheetapp1.application.core.timesheetstatus.TimesheetstatusAppService;

import com.fastcode.timesheetapp1.domain.extended.timesheetstatus.ITimesheetstatusRepositoryExtended;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;

@Service("timesheetstatusAppServiceExtended")
public class TimesheetstatusAppServiceExtended extends TimesheetstatusAppService implements ITimesheetstatusAppServiceExtended {

	public TimesheetstatusAppServiceExtended(ITimesheetstatusRepositoryExtended timesheetstatusRepositoryExtended,
				ITimesheetstatusMapperExtended mapper,LoggingHelper logHelper) {

		super(timesheetstatusRepositoryExtended,
		mapper,logHelper);

	}

 	//Add your custom code here
 
}


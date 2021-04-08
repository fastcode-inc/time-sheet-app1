package com.fastcode.timesheetapp1.restcontrollers.extended;

import com.fastcode.timesheetapp1.application.extended.timeofftype.ITimeofftypeAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.timesheetdetails.ITimesheetdetailsAppServiceExtended;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;
import com.fastcode.timesheetapp1.restcontrollers.core.TimeofftypeController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timeofftype/extended")
public class TimeofftypeControllerExtended extends TimeofftypeController {

		public TimeofftypeControllerExtended(ITimeofftypeAppServiceExtended timeofftypeAppServiceExtended, ITimesheetdetailsAppServiceExtended timesheetdetailsAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		timeofftypeAppServiceExtended,
    	timesheetdetailsAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}


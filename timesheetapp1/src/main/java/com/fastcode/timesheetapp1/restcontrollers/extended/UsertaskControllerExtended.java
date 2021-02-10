package com.fastcode.timesheetapp1.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheetapp1.restcontrollers.core.UsertaskController;
import com.fastcode.timesheetapp1.application.extended.usertask.IUsertaskAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.task.ITaskAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.authorization.users.IUsersAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/usertask/extended")
public class UsertaskControllerExtended extends UsertaskController {

		public UsertaskControllerExtended(IUsertaskAppServiceExtended usertaskAppServiceExtended, ITaskAppServiceExtended taskAppServiceExtended, IUsersAppServiceExtended usersAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		usertaskAppServiceExtended,
    	taskAppServiceExtended,
    	usersAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}


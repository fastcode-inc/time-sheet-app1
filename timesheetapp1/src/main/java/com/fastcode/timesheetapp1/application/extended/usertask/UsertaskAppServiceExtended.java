package com.fastcode.timesheetapp1.application.extended.usertask;

import org.springframework.stereotype.Service;
import com.fastcode.timesheetapp1.application.core.usertask.UsertaskAppService;

import com.fastcode.timesheetapp1.domain.extended.usertask.IUsertaskRepositoryExtended;
import com.fastcode.timesheetapp1.domain.extended.task.ITaskRepositoryExtended;
import com.fastcode.timesheetapp1.domain.extended.authorization.users.IUsersRepositoryExtended;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;

@Service("usertaskAppServiceExtended")
public class UsertaskAppServiceExtended extends UsertaskAppService implements IUsertaskAppServiceExtended {

	public UsertaskAppServiceExtended(IUsertaskRepositoryExtended usertaskRepositoryExtended,
				ITaskRepositoryExtended taskRepositoryExtended,IUsersRepositoryExtended usersRepositoryExtended,IUsertaskMapperExtended mapper,LoggingHelper logHelper) {

		super(usertaskRepositoryExtended,
		taskRepositoryExtended,usersRepositoryExtended,mapper,logHelper);

	}

 	//Add your custom code here
 
}


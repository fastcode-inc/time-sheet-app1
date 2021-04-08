package com.fastcode.timesheetapp1.application.extended.authorization.usersrole;

import com.fastcode.timesheetapp1.application.core.authorization.usersrole.UsersroleAppService;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;
import com.fastcode.timesheetapp1.domain.extended.authorization.role.IRoleRepositoryExtended;
import com.fastcode.timesheetapp1.domain.extended.authorization.users.IUsersRepositoryExtended;
import com.fastcode.timesheetapp1.domain.extended.authorization.usersrole.IUsersroleRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("usersroleAppServiceExtended")
public class UsersroleAppServiceExtended extends UsersroleAppService implements IUsersroleAppServiceExtended {

	public UsersroleAppServiceExtended(IUsersroleRepositoryExtended usersroleRepositoryExtended,
				IRoleRepositoryExtended roleRepositoryExtended,IUsersRepositoryExtended usersRepositoryExtended,IUsersroleMapperExtended mapper,LoggingHelper logHelper) {

		super(usersroleRepositoryExtended,
		roleRepositoryExtended,usersRepositoryExtended,mapper,logHelper);

	}

 	//Add your custom code here
 
}


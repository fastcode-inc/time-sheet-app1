package com.fastcode.timesheetapp1.application.extended.usertask;

import com.fastcode.timesheetapp1.application.core.usertask.IUsertaskAppService;
import com.fastcode.timesheetapp1.application.extended.usertask.dto.UsertaskOutput;

import java.util.List;

public interface IUsertaskAppServiceExtended extends IUsertaskAppService {

	//Add your custom code here
	public List<UsertaskOutput> getUsertaskList(Long userId);
}


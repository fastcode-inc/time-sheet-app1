package com.fastcode.timesheetapp1.application.extended.task;

import com.fastcode.timesheetapp1.application.core.task.TaskAppService;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;
import com.fastcode.timesheetapp1.domain.extended.project.IProjectRepositoryExtended;
import com.fastcode.timesheetapp1.domain.extended.task.ITaskRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("taskAppServiceExtended")
public class TaskAppServiceExtended extends TaskAppService implements ITaskAppServiceExtended {

	public TaskAppServiceExtended(ITaskRepositoryExtended taskRepositoryExtended,
				IProjectRepositoryExtended projectRepositoryExtended,ITaskMapperExtended mapper,LoggingHelper logHelper) {

		super(taskRepositoryExtended,
		projectRepositoryExtended,mapper,logHelper);

	}

 	//Add your custom code here
 
}


package com.fastcode.timesheetapp1.domain.extended.task;

import com.fastcode.timesheetapp1.domain.core.task.ITaskRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("taskRepositoryExtended")
public interface ITaskRepositoryExtended extends ITaskRepository {

	//Add your custom code here
}


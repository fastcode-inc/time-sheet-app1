package com.fastcode.timesheetapp1.domain.extended.timesheetstatus;

import com.fastcode.timesheetapp1.domain.core.timesheetstatus.ITimesheetstatusRepository;
import com.fastcode.timesheetapp1.domain.core.timesheetstatus.TimesheetstatusEntity;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("timesheetstatusRepositoryExtended")
public interface ITimesheetstatusRepositoryExtended extends ITimesheetstatusRepository {

	//Add your custom code here
	TimesheetstatusEntity findByStatusnameIgnoreCase(String statusname);
}


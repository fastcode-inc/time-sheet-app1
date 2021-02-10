package com.fastcode.timesheetapp1.domain.extended.timesheet;

import org.springframework.stereotype.Repository;
import com.fastcode.timesheetapp1.domain.core.timesheet.ITimesheetRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;

@JaversSpringDataAuditable
@Repository("timesheetRepositoryExtended")
public interface ITimesheetRepositoryExtended extends ITimesheetRepository {

	//Add your custom code here
}


package com.fastcode.timesheetapp1.domain.extended.timesheetdetails;

import org.springframework.stereotype.Repository;
import com.fastcode.timesheetapp1.domain.core.timesheetdetails.ITimesheetdetailsRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;

@JaversSpringDataAuditable
@Repository("timesheetdetailsRepositoryExtended")
public interface ITimesheetdetailsRepositoryExtended extends ITimesheetdetailsRepository {

	//Add your custom code here
}


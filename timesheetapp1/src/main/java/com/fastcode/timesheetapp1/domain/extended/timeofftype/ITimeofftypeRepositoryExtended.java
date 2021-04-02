package com.fastcode.timesheetapp1.domain.extended.timeofftype;

import com.fastcode.timesheetapp1.domain.core.timeofftype.ITimeofftypeRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("timeofftypeRepositoryExtended")
public interface ITimeofftypeRepositoryExtended extends ITimeofftypeRepository {

	//Add your custom code here
}


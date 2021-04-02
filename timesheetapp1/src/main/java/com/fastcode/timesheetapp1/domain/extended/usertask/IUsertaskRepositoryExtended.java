package com.fastcode.timesheetapp1.domain.extended.usertask;

import com.fastcode.timesheetapp1.domain.core.usertask.IUsertaskRepository;
import com.fastcode.timesheetapp1.domain.core.usertask.UsertaskEntity;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

import java.util.List;

@JaversSpringDataAuditable
@Repository("usertaskRepositoryExtended")
public interface IUsertaskRepositoryExtended extends IUsertaskRepository {

	//Add your custom code here
	List<UsertaskEntity> findByUserid(Long userId);
}


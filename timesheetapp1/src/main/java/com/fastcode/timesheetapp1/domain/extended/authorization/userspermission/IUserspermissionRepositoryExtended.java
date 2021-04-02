package com.fastcode.timesheetapp1.domain.extended.authorization.userspermission;

import com.fastcode.timesheetapp1.domain.core.authorization.userspermission.IUserspermissionRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("userspermissionRepositoryExtended")
public interface IUserspermissionRepositoryExtended extends IUserspermissionRepository {

	//Add your custom code here
}


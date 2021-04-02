package com.fastcode.timesheetapp1.domain.core.usertask;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("usertaskRepository")
public interface IUsertaskRepository
    extends JpaRepository<UsertaskEntity, UsertaskId>, QuerydslPredicateExecutor<UsertaskEntity> {}

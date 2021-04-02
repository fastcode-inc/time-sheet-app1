package com.fastcode.timesheetapp1.domain.core.timeofftype;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("timeofftypeRepository")
public interface ITimeofftypeRepository
    extends JpaRepository<TimeofftypeEntity, Long>, QuerydslPredicateExecutor<TimeofftypeEntity> {}

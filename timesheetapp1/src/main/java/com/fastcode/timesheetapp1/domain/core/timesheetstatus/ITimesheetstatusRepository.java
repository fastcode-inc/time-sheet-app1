package com.fastcode.timesheetapp1.domain.core.timesheetstatus;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("timesheetstatusRepository")
public interface ITimesheetstatusRepository
    extends JpaRepository<TimesheetstatusEntity, Long>, QuerydslPredicateExecutor<TimesheetstatusEntity> {}

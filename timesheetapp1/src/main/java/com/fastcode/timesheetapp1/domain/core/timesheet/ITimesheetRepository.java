package com.fastcode.timesheetapp1.domain.core.timesheet;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("timesheetRepository")
public interface ITimesheetRepository
    extends JpaRepository<TimesheetEntity, Long>, QuerydslPredicateExecutor<TimesheetEntity> {}

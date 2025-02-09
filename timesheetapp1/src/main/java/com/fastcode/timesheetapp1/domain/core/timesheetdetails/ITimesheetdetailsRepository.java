package com.fastcode.timesheetapp1.domain.core.timesheetdetails;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("timesheetdetailsRepository")
public interface ITimesheetdetailsRepository
    extends JpaRepository<TimesheetdetailsEntity, Long>, QuerydslPredicateExecutor<TimesheetdetailsEntity> {}

package com.fastcode.timesheetapp1.domain.core.task;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("taskRepository")
public interface ITaskRepository extends JpaRepository<TaskEntity, Long>, QuerydslPredicateExecutor<TaskEntity> {}

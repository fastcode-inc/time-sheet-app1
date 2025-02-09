package com.fastcode.timesheetapp1.domain.core.authorization.rolepermission;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@JaversSpringDataAuditable
@Repository("rolepermissionRepository")
public interface IRolepermissionRepository
    extends JpaRepository<RolepermissionEntity, RolepermissionId>, QuerydslPredicateExecutor<RolepermissionEntity> {
    List<RolepermissionEntity> findByRoleId(Long value);
}

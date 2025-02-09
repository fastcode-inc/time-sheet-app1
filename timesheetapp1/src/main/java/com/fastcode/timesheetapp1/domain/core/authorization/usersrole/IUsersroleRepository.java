package com.fastcode.timesheetapp1.domain.core.authorization.usersrole;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@JaversSpringDataAuditable
@Repository("usersroleRepository")
public interface IUsersroleRepository
    extends JpaRepository<UsersroleEntity, UsersroleId>, QuerydslPredicateExecutor<UsersroleEntity> {
    List<UsersroleEntity> findByUsersId(Long usersId);

    List<UsersroleEntity> findByRoleId(Long roleId);
}

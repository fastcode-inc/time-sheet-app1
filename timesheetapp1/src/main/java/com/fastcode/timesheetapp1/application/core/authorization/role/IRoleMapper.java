package com.fastcode.timesheetapp1.application.core.authorization.role;

import com.fastcode.timesheetapp1.application.core.authorization.role.dto.*;
import com.fastcode.timesheetapp1.domain.core.authorization.role.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper {
    RoleEntity createRoleInputToRoleEntity(CreateRoleInput roleDto);
    CreateRoleOutput roleEntityToCreateRoleOutput(RoleEntity entity);

    RoleEntity updateRoleInputToRoleEntity(UpdateRoleInput roleDto);

    UpdateRoleOutput roleEntityToUpdateRoleOutput(RoleEntity entity);

    FindRoleByIdOutput roleEntityToFindRoleByIdOutput(RoleEntity entity);

    FindRoleByNameOutput roleEntityToFindRoleByNameOutput(RoleEntity entity);
}

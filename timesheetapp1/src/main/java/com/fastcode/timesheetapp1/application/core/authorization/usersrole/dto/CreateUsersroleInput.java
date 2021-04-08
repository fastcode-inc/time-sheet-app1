package com.fastcode.timesheetapp1.application.core.authorization.usersrole.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUsersroleInput {

    @NotNull(message = "roleId Should not be null")
    private Long roleId;

    @NotNull(message = "usersId Should not be null")
    private Long usersId;
}

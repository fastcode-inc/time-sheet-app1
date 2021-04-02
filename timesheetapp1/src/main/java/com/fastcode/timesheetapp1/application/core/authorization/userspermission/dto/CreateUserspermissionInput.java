package com.fastcode.timesheetapp1.application.core.authorization.userspermission.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserspermissionInput {

    @NotNull(message = "permissionId Should not be null")
    private Long permissionId;

    private Boolean revoked;

    @NotNull(message = "usersId Should not be null")
    private Long usersId;
}

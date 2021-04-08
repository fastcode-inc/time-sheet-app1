package com.fastcode.timesheetapp1.application.core.authorization.users.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResetPasswordInput {

    @NotNull
    String token;

    @NotNull
    String password;
}

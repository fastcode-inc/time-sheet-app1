package com.fastcode.timesheetapp1.application.core.authorization.users.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdatePasswordInput {

    @NotNull
    @Length(min = 8, max = 128, message = "password must be between 8 and 128 characters")
    String oldPassword;

    @NotNull
    @Length(min = 8, max = 128, message = "password must be between 8 and 128 characters")
    String newPassword;
}

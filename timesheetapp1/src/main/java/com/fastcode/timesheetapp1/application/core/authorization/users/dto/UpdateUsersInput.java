package com.fastcode.timesheetapp1.application.core.authorization.users.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
public class UpdateUsersInput {

    @Pattern(
        regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
        message = "Email Address should be valid"
    )
    @NotNull(message = "emailaddress Should not be null")
    @Length(max = 255, message = "emailaddress must be less than 255 characters")
    private String emailaddress;

    @NotNull(message = "firstname Should not be null")
    @Length(max = 255, message = "firstname must be less than 255 characters")
    private String firstname;

    @NotNull(message = "id Should not be null")
    private Long id;

    @NotNull(message = "isactive Should not be null")
    private Boolean isactive = false;

    private LocalDate joinDate;

    @NotNull(message = "lastname Should not be null")
    @Length(max = 255, message = "lastname must be less than 255 characters")
    private String lastname;

    @Length(max = 255, message = "password must be less than 255 characters")
    private String password;

    @NotNull(message = "username Should not be null")
    @Length(max = 255, message = "username must be less than 255 characters")
    private String username;

    private Long versiono;
}

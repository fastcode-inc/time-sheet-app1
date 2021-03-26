package com.fastcode.timesheetapp1.application.core.authorization.users.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserspreferenceOutput {

    private String firstname;
    private String triggerName;
    private Boolean isactive;
    private String password;
    private LocalDate joinDate;
    private Long id;
    private String emailaddress;
    private String lastname;
    private String triggerGroup;
    private String username;
    private Long usersId;
}

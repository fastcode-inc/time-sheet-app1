package com.fastcode.timesheetapp1.application.core.timesheet.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetUsersOutput {

    private String emailaddress;
    private String firstname;
    private Long id;
    private Boolean isactive;
    private LocalDate joinDate;
    private String lastname;
    private String password;
    private String triggerGroup;
    private String triggerName;
    private String username;
    private Long timesheetId;
}

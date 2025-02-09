package com.fastcode.timesheetapp1.application.core.authorization.userspermission.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUsersOutput {

    private String firstname;
    private Boolean isactive;
    private String password;
    private Long id;
    private String emailaddress;
    private String lastname;
    private String username;
    private Long userspermissionPermissionId;
    private Long userspermissionUsersId;
}

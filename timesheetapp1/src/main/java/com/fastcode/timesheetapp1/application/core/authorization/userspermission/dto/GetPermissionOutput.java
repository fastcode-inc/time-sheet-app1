package com.fastcode.timesheetapp1.application.core.authorization.userspermission.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPermissionOutput {

    private Long id;
    private String name;
    private String displayName;
    private Long userspermissionPermissionId;
    private Long userspermissionUsersId;
}

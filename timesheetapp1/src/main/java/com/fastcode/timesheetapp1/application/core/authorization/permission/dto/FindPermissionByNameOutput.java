package com.fastcode.timesheetapp1.application.core.authorization.permission.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindPermissionByNameOutput {

    private String displayName;
    private Long id;
    private String name;
}

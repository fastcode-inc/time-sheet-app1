package com.fastcode.timesheetapp1.application.core.authorization.role.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindRoleByNameOutput {

    private String displayName;
    private Long id;
    private String name;
}

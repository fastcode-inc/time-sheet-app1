package com.fastcode.timesheetapp1.application.core.authorization.rolepermission.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRolepermissionOutput {

    private Long permissionId;
    private Long roleId;
    private String permissionDescriptiveField;
    private String roleDescriptiveField;
}

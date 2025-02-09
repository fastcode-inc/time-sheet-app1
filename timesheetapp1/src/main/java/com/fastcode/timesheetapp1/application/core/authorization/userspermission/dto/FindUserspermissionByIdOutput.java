package com.fastcode.timesheetapp1.application.core.authorization.userspermission.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUserspermissionByIdOutput {

    private Long permissionId;
    private Boolean revoked;
    private Long usersId;
    private String permissionDescriptiveField;
    private String usersDescriptiveField;
    private Long versiono;
}

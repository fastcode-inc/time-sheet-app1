package com.fastcode.timesheetapp1.application.core.authorization.usersrole.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUsersroleByIdOutput {

    private Long roleId;
    private Long usersId;
    private String roleDescriptiveField;
    private String usersDescriptiveField;
    private Long versiono;
}

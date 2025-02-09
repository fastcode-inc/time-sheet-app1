package com.fastcode.timesheetapp1.application.core.usertask.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateUsertaskInput {

    @NotNull(message = "taskid Should not be null")
    private Long taskid;

    @NotNull(message = "userid Should not be null")
    private Long userid;

    private Long versiono;
}

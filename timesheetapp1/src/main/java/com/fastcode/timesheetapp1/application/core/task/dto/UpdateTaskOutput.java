package com.fastcode.timesheetapp1.application.core.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskOutput {

    private String description;
    private Long id;
    private Boolean isactive;
    private String name;
    private Long projectid;
    private String projectDescriptiveField;
}

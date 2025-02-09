package com.fastcode.timesheetapp1.application.core.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCustomerOutput {

    private Long customerid;
    private String description;
    private Boolean isactive;
    private String name;
    private Long projectId;
}

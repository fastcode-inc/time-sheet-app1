package com.fastcode.timesheetapp1.application.core.usertask.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTaskOutput {

    private String description;
    private Long id;
    private Boolean isactive;
    private String name;
    private Long usertaskTaskid;
    private Long usertaskUserid;
}

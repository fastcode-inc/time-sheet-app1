package com.fastcode.timesheetapp1.application.core.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetProjectOutput {

    private String description;
    private LocalDate enddate;
    private Long id;
    private String name;
    private LocalDate startdate;
    private Long taskId;
}

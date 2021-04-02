package com.fastcode.timesheetapp1.application.core.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FindProjectByIdOutput {

    private String description;
    private LocalDate enddate;
    private Long id;
    private String name;
    private LocalDate startdate;
    private Long customerid;
    private String customerDescriptiveField;
    private Long versiono;
}

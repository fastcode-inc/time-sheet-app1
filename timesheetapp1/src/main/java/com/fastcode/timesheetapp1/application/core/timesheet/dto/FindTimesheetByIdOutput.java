package com.fastcode.timesheetapp1.application.core.timesheet.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FindTimesheetByIdOutput {

    private Long id;
    private String notes;
    private LocalDate periodendingdate;
    private LocalDate periodstartingdate;
    private Long timesheetstatusid;
    private String timesheetstatusDescriptiveField;
    private Long userid;
    private String usersDescriptiveField;
    private Long versiono;
}

package com.fastcode.timesheetapp1.application.core.timesheet.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UpdateTimesheetInput {

    @NotNull(message = "id Should not be null")
    private Long id;

    private String notes;

    @NotNull(message = "periodendingdate Should not be null")
    private LocalDate periodendingdate;

    @NotNull(message = "periodstartingdate Should not be null")
    private LocalDate periodstartingdate;

    private Long timesheetstatusid;
    private Long userid;
    private Long versiono;
}

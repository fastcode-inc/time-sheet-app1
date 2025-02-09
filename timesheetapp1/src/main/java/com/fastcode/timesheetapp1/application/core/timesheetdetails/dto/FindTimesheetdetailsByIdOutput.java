package com.fastcode.timesheetapp1.application.core.timesheetdetails.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class FindTimesheetdetailsByIdOutput {

    private BigDecimal hours;
    private Long id;
    private String notes;
    private LocalDate workdate;
    private Long taskid;
    private String taskDescriptiveField;
    private Long timeofftypeid;
    private String timeofftypeDescriptiveField;
    private Long timesheetid;
    private LocalDate timesheetDescriptiveField;
    private Long versiono;
}

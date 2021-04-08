package com.fastcode.timesheetapp1.application.core.timesheetdetails.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateTimesheetdetailsInput {

    private BigDecimal hours;

    private String notes;

    @NotNull(message = "workdate Should not be null")
    private LocalDate workdate;

    private Long taskid;
    private Long timeofftypeid;
    private Long timesheetid;
}

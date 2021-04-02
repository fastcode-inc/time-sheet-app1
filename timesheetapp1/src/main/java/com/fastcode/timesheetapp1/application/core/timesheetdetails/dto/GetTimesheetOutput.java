package com.fastcode.timesheetapp1.application.core.timesheetdetails.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetTimesheetOutput {

    private Long id;
    private String notes;
    private LocalDate periodendingdate;
    private LocalDate periodstartingdate;
    private Long timesheetdetailsId;
}

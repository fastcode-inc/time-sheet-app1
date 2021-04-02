package com.fastcode.timesheetapp1.addons.reporting.application.dashboard.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ExistingReportInput {

    @NotNull
    private Long id;

    private String reportWidth;
}

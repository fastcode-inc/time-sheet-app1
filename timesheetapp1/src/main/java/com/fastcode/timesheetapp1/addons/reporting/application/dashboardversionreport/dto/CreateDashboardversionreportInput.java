package com.fastcode.timesheetapp1.addons.reporting.application.dashboardversionreport.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateDashboardversionreportInput {

    @NotNull(message = "dashboardId Should not be null")
    private Long dashboardId;

    private Long userId;
    private String dashboardVersion;
    private String reportWidth;
    private Long orderId;

    @NotNull(message = "reportId Should not be null")
    private Long reportId;
}

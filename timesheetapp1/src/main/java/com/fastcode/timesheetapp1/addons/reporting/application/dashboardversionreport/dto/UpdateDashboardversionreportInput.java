package com.fastcode.timesheetapp1.addons.reporting.application.dashboardversionreport.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateDashboardversionreportInput {

    @NotNull(message = "dashboardId Should not be null")
    private Long dashboardId;

    @NotNull(message = "reportId Should not be null")
    private Long reportId;

    private Long userId;
    private String dashboardVersion;
    private String reportWidth;
    private Long orderId;
    private Long versiono;
}

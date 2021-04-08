package com.fastcode.timesheetapp1.addons.reporting.application.dashboardversion.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateDashboardversionInput {

    private String description;

    @NotNull(message = "id Should not be null")
    private Long id;

    private String title;
    private Boolean isRefreshed;
    private Long userId;
    private Long dashboardId;
    private String dashboardVersion;
    private Long versiono;
}

package com.fastcode.timesheetapp1.addons.reporting.application.dashboardversion.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDashboardversionInput {

    private String description;
    private String title;
    private Long userId;
    private Long dashboardId;
}

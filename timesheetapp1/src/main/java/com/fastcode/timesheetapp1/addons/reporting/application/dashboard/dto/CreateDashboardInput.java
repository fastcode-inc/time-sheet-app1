package com.fastcode.timesheetapp1.addons.reporting.application.dashboard.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateDashboardInput {

    private String description;
    private Boolean isPublished;

    @NotNull
    private String title;

    private Long ownerId;
}

package com.fastcode.timesheetapp1.addons.reporting.application.dashboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AddExistingReportToNewDashboardInput {

    private Long id;
    private String description;
    private String title;
    private Long ownerId;
    private Boolean isPublished;
    List<ExistingReportInput> reportDetails = new ArrayList<>();
}

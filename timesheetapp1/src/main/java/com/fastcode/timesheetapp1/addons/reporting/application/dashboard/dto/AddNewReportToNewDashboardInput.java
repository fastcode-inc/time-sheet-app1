package com.fastcode.timesheetapp1.addons.reporting.application.dashboard.dto;

import com.fastcode.timesheetapp1.addons.reporting.application.report.dto.CreateReportInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AddNewReportToNewDashboardInput {

    private String description;
    private String title;
    private Long ownerId;
    private Boolean isPublished;
    List<CreateReportInput> reportDetails = new ArrayList<>();
}

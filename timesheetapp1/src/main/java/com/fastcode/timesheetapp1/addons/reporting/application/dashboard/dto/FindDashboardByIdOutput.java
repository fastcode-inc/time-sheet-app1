package com.fastcode.timesheetapp1.addons.reporting.application.dashboard.dto;

import com.fastcode.timesheetapp1.addons.reporting.application.report.dto.FindReportByIdOutput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindDashboardByIdOutput {

    private String description;
    private String title;
    private Long userId;
    private Long id;
    private Boolean isPublished;
    private Boolean isRefreshed;
    private Long ownerId;
    private String ownerDescriptiveField;
    private List<FindReportByIdOutput> reportDetails;
    private Long versiono;
}

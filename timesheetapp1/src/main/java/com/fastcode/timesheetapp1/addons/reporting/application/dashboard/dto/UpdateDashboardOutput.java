package com.fastcode.timesheetapp1.addons.reporting.application.dashboard.dto;

import com.fastcode.timesheetapp1.addons.reporting.application.report.dto.FindReportByIdOutput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateDashboardOutput {

    private String description;
    private Long id;
    private String title;
    private Long ownerId;
    private String ownerDescriptiveField;
    private List<FindReportByIdOutput> reportDetails;
    private Boolean isRefreshed;
    private Long versiono;
}

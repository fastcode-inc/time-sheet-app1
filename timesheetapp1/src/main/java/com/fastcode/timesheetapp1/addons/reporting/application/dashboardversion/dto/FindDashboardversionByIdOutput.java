package com.fastcode.timesheetapp1.addons.reporting.application.dashboardversion.dto;

import com.fastcode.timesheetapp1.addons.reporting.application.report.dto.FindReportByIdOutput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindDashboardversionByIdOutput {

    private String description;
    private Long id;
    private String title;
    private Boolean isRefreshed;
    private Long userId;
    private String userDescriptiveField;
    private List<FindReportByIdOutput> reportDetails;
    private Long versiono;
}

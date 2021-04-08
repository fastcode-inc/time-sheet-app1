package com.fastcode.timesheetapp1.addons.reporting.application.dashboard.dto;

import com.fastcode.timesheetapp1.addons.reporting.application.report.dto.UpdateReportInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateDashboardInput {

    private String description;

    @NotNull(message = "id Should not be null")
    private Long id;

    @NotNull
    private String title;

    private Long ownerId;
    private Long userId;
    private Boolean isPublished;
    List<UpdateReportInput> reportDetails = new ArrayList<>();
    private Long versiono;
}

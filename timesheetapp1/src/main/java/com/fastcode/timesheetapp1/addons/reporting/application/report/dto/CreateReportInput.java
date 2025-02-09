package com.fastcode.timesheetapp1.addons.reporting.application.report.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.json.simple.JSONObject;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateReportInput {

    private Long ownerId;
    private Boolean isPublished;
    private String ctype;
    private String description;
    private JSONObject query;

    @Length(max = 255, message = "reportType must be less than 255 characters")
    private String reportType;

    @NotNull
    private String title;

    private String reportWidth;
}

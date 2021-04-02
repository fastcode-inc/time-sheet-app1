package com.fastcode.timesheetapp1.application.core.timesheetstatus.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateTimesheetstatusInput {

    @NotNull(message = "id Should not be null")
    private Long id;

    @NotNull(message = "statusname Should not be null")
    @Length(max = 255, message = "statusname must be less than 255 characters")
    private String statusname;

    private Long versiono;
}

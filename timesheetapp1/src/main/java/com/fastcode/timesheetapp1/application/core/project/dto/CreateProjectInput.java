package com.fastcode.timesheetapp1.application.core.project.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CreateProjectInput {

    private String description;

    @NotNull(message = "enddate Should not be null")
    private LocalDate enddate;

    @NotNull(message = "name Should not be null")
    @Length(max = 255, message = "name must be less than 255 characters")
    private String name;

    @NotNull(message = "startdate Should not be null")
    private LocalDate startdate;

    private Long customerid;
}

package com.fastcode.timesheetapp1.application.core.customer.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateCustomerInput {

    private String description;

    @NotNull(message = "isactive Should not be null")
    private Boolean isactive;

    @NotNull(message = "name Should not be null")
    @Length(max = 255, message = "name must be less than 255 characters")
    private String name;
}

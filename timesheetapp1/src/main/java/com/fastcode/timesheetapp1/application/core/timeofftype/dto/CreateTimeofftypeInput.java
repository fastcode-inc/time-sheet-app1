package com.fastcode.timesheetapp1.application.core.timeofftype.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateTimeofftypeInput {

    @NotNull(message = "typename Should not be null")
    @Length(max = 255, message = "typename must be less than 255 characters")
    private String typename;
}

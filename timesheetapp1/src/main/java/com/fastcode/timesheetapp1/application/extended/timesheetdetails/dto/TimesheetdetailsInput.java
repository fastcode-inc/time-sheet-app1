package com.fastcode.timesheetapp1.application.extended.timesheetdetails.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class TimesheetdetailsInput {

  	private Long id;
  	private Long taskid;
  	private Long timesheetid;
  	private Long timeofftypeid;
  	
  	@NotNull(message = "workdate Should not be null")
  	private LocalDate workdate;
  	private BigDecimal hours;
  	private String notes;
}

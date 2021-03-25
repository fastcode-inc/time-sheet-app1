package com.fastcode.timesheetapp1.addons.scheduler.application.trigger.dto;

import java.time.OffsetTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmailTriggerInfo {
	
	String days;
	OffsetTime time;

}

package com.fastcode.timesheetapp1.addons.scheduler.application.trigger.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetTime;

@Getter @Setter
public class EmailTriggerInfo {
	
	String days;
	OffsetTime time;

}

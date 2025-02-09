package com.fastcode.timesheetapp1.addons.scheduler.application.job.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GetJobOutput {

    private Long id;
    private String jobName;
    private String jobDescription;
    private String jobGroup;
    private String jobClass;
    private String triggerName;
    private String triggerGroup;
    private Date firedTime;
    private Date finishedTime;
    private String duration;
    private String jobStatus;
    private Map<String, String> jobMapData = new HashMap<String, String>();

    public GetJobOutput() {
        super();
    }
}

package com.fastcode.timesheetapp1.addons.reporting.domain.dashboardversionreport;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DashboardversionreportId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long dashboardId;
    private Long userId;
    private String dashboardVersion;
    private Long reportId;

    public DashboardversionreportId(Long dashboardId, Long userId, String dashboardVersion, Long reportId) {
        this.dashboardId = dashboardId;
        this.userId = userId;
        this.dashboardVersion = dashboardVersion;
        this.reportId = reportId;
    }
}

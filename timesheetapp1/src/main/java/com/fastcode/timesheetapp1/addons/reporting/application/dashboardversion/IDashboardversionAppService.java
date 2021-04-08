package com.fastcode.timesheetapp1.addons.reporting.application.dashboardversion;

import com.fastcode.timesheetapp1.addons.reporting.application.dashboardversion.dto.*;
import com.fastcode.timesheetapp1.addons.reporting.domain.dashboardversion.DashboardversionId;
import com.fastcode.timesheetapp1.commons.search.SearchCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IDashboardversionAppService {
    CreateDashboardversionOutput create(CreateDashboardversionInput dashboardversion);

    void delete(DashboardversionId id);

    UpdateDashboardversionOutput update(DashboardversionId id, UpdateDashboardversionInput input);

    FindDashboardversionByIdOutput findById(DashboardversionId id);

    List<FindDashboardversionByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;

    Map<String, String> parseReportdashboardJoinColumn(String keysString);

    //User
    GetUsersOutput getUsers(DashboardversionId dashboardversionid);
}

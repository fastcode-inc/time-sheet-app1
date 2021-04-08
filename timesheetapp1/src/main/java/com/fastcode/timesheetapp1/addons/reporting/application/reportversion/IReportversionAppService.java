package com.fastcode.timesheetapp1.addons.reporting.application.reportversion;

import com.fastcode.timesheetapp1.addons.reporting.application.reportversion.dto.*;
import com.fastcode.timesheetapp1.addons.reporting.domain.reportversion.ReportversionId;
import com.fastcode.timesheetapp1.commons.search.SearchCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IReportversionAppService {
    CreateReportversionOutput create(CreateReportversionInput report);

    void delete(ReportversionId id);

    UpdateReportversionOutput update(ReportversionId id, UpdateReportversionInput input);

    FindReportversionByIdOutput findById(ReportversionId id);

    List<FindReportversionByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;

    GetUsersOutput getUsers(ReportversionId reportid);

    List<FindReportversionByIdOutput> findByUsersId(Long userId);

    Map<String, String> parsedashboardversionreportJoinColumn(String keysString);
}

package com.fastcode.timesheetapp1.addons.reporting.application.report;

import com.fastcode.timesheetapp1.addons.reporting.application.report.dto.*;
import com.fastcode.timesheetapp1.commons.search.SearchCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IReportAppService {
    CreateReportOutput create(CreateReportInput report);

    void delete(Long id, Long userId);

    UpdateReportOutput update(Long id, UpdateReportInput input);

    FindReportByIdOutput findById(Long id);

    List<FindReportByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;

    List<ReportDetailsOutput> getReports(Long userId, String search, Pageable pageable) throws Exception;

    FindReportByIdOutput findByReportIdAndUsersId(Long reportId, Long userId, String version);

    ReportDetailsOutput publishReport(Long userId, Long reportId);

    ReportDetailsOutput refreshReport(Long userId, Long reportId);

    Map<String, String> parseReportdashboardJoinColumn(String keysString);
}

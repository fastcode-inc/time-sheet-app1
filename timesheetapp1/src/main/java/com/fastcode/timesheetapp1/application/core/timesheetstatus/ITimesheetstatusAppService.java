package com.fastcode.timesheetapp1.application.core.timesheetstatus;

import com.fastcode.timesheetapp1.application.core.timesheetstatus.dto.*;
import com.fastcode.timesheetapp1.commons.search.SearchCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ITimesheetstatusAppService {
    //CRUD Operations

    CreateTimesheetstatusOutput create(CreateTimesheetstatusInput timesheetstatus);

    void delete(Long id);

    UpdateTimesheetstatusOutput update(Long id, UpdateTimesheetstatusInput input);

    FindTimesheetstatusByIdOutput findById(Long id);

    List<FindTimesheetstatusByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;

    //Join Column Parsers

    Map<String, String> parseTimesheetsJoinColumn(String keysString);
}

package com.fastcode.timesheetapp1.application.core.timesheetdetails;

import com.fastcode.timesheetapp1.application.core.timesheetdetails.dto.*;
import com.fastcode.timesheetapp1.commons.search.SearchCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITimesheetdetailsAppService {
    //CRUD Operations

    CreateTimesheetdetailsOutput create(CreateTimesheetdetailsInput timesheetdetails);

    void delete(Long id);

    UpdateTimesheetdetailsOutput update(Long id, UpdateTimesheetdetailsInput input);

    FindTimesheetdetailsByIdOutput findById(Long id);

    List<FindTimesheetdetailsByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;
    //Relationship Operations
    //Relationship Operations
    //Relationship Operations

    GetTaskOutput getTask(Long timesheetdetailsid);

    GetTimeofftypeOutput getTimeofftype(Long timesheetdetailsid);

    GetTimesheetOutput getTimesheet(Long timesheetdetailsid);
    //Join Column Parsers
}

package com.fastcode.timesheetapp1.application.core.task;

import com.fastcode.timesheetapp1.application.core.task.dto.*;
import com.fastcode.timesheetapp1.commons.search.SearchCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ITaskAppService {
    //CRUD Operations

    CreateTaskOutput create(CreateTaskInput task);

    void delete(Long id);

    UpdateTaskOutput update(Long id, UpdateTaskInput input);

    FindTaskByIdOutput findById(Long id);

    List<FindTaskByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;
    //Relationship Operations

    GetProjectOutput getProject(Long taskid);

    //Join Column Parsers

    Map<String, String> parseTimesheetdetailsJoinColumn(String keysString);

    Map<String, String> parseUsertasksJoinColumn(String keysString);
}

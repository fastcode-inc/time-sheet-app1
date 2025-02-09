package com.fastcode.timesheetapp1.application.core.authorization.role;

import com.fastcode.timesheetapp1.application.core.authorization.role.dto.*;
import com.fastcode.timesheetapp1.commons.search.SearchCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IRoleAppService {
    //CRUD Operations

    CreateRoleOutput create(CreateRoleInput role);

    void delete(Long id);

    UpdateRoleOutput update(Long id, UpdateRoleInput input);

    FindRoleByIdOutput findById(Long id);

    List<FindRoleByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;

    FindRoleByNameOutput findByRoleName(String roleName);

    //Join Column Parsers

    Map<String, String> parseRolepermissionsJoinColumn(String keysString);

    Map<String, String> parseUsersrolesJoinColumn(String keysString);
}

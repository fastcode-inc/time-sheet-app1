package com.fastcode.timesheetapp1.application.core.authorization.usersrole;

import com.fastcode.timesheetapp1.application.core.authorization.usersrole.dto.*;
import com.fastcode.timesheetapp1.commons.search.SearchCriteria;
import com.fastcode.timesheetapp1.domain.core.authorization.usersrole.UsersroleId;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsersroleAppService {
    //CRUD Operations

    CreateUsersroleOutput create(CreateUsersroleInput usersrole);

    void delete(UsersroleId usersroleId);

    UpdateUsersroleOutput update(UsersroleId usersroleId, UpdateUsersroleInput input);

    FindUsersroleByIdOutput findById(UsersroleId usersroleId);

    List<FindUsersroleByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;
    //Relationship Operations
    //Relationship Operations

    GetRoleOutput getRole(UsersroleId usersroleId);

    GetUsersOutput getUsers(UsersroleId usersroleId);

    //Join Column Parsers

    UsersroleId parseUsersroleKey(String keysString);
}

package com.fastcode.timesheetapp1.domain.core.authorization.usersrole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UsersroleId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;
    private Long usersId;

    public UsersroleId(Long roleId, Long usersId) {
        this.roleId = roleId;
        this.usersId = usersId;
    }
}

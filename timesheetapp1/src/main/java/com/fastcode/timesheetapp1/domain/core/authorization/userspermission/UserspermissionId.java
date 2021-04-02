package com.fastcode.timesheetapp1.domain.core.authorization.userspermission;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserspermissionId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long permissionId;
    private Long usersId;

    public UserspermissionId(Long permissionId, Long usersId) {
        this.permissionId = permissionId;
        this.usersId = usersId;
    }
}

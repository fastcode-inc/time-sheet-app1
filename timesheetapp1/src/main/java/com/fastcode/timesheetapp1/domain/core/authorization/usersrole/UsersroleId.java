package com.fastcode.timesheetapp1.domain.core.authorization.usersrole;

import java.time.*;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter
@NoArgsConstructor
public class UsersroleId implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Long usersId;
    private Long roleId;
    
    public UsersroleId(Long roleId,Long usersId) {
 	this.usersId = usersId;
 	this.roleId = roleId;
    }
    
}

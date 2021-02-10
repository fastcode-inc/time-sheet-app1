package com.fastcode.timesheetapp1.domain.core.authorization.tokenverification;

import java.time.*;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter
@NoArgsConstructor
public class TokenverificationId implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Long usersId;
    private String tokenType;
    
    public TokenverificationId(String tokenType,Long usersId) {
 	this.usersId = usersId;
 	this.tokenType = tokenType;
    }
    
}

package com.fastcode.timesheetapp1.domain.core.authorization.tokenverification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TokenverificationId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tokenType;
    private Long usersId;

    public TokenverificationId(String tokenType, Long usersId) {
        this.tokenType = tokenType;
        this.usersId = usersId;
    }
}

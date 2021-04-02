package com.fastcode.timesheetapp1.domain.core.usertask;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UsertaskId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long taskid;
    private Long userid;

    public UsertaskId(Long taskid, Long userid) {
        this.taskid = taskid;
        this.userid = userid;
    }
}

package com.fastcode.timesheetapp1.domain.core.abstractentity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    @EqualsAndHashCode.Include
    @Column(name = "VERSIONO", nullable = false)
    private Long versiono;
}

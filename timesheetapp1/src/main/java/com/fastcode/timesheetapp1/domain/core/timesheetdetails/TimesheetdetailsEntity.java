package com.fastcode.timesheetapp1.domain.core.timesheetdetails;

import com.fastcode.timesheetapp1.domain.core.abstractentity.AbstractEntity;
import com.fastcode.timesheetapp1.domain.core.task.TaskEntity;
import com.fastcode.timesheetapp1.domain.core.timeofftype.TimeofftypeEntity;
import com.fastcode.timesheetapp1.domain.core.timesheet.TimesheetEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "timesheetdetails")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TimesheetdetailsEntity extends AbstractEntity {

    @Basic
    @Column(name = "hours", nullable = true)
    private BigDecimal hours;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "notes", nullable = true)
    private String notes;

    @Basic
    @Column(name = "workdate", nullable = false)
    private LocalDate workdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskid")
    private TaskEntity task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timeofftypeid")
    private TimeofftypeEntity timeofftype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timesheetid")
    private TimesheetEntity timesheet;
}

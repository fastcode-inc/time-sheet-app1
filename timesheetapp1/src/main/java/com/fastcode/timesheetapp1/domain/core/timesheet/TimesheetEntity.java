package com.fastcode.timesheetapp1.domain.core.timesheet;

import com.fastcode.timesheetapp1.domain.core.abstractentity.AbstractEntity;
import com.fastcode.timesheetapp1.domain.core.authorization.users.UsersEntity;
import com.fastcode.timesheetapp1.domain.core.timesheetdetails.TimesheetdetailsEntity;
import com.fastcode.timesheetapp1.domain.core.timesheetstatus.TimesheetstatusEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javers.core.metamodel.annotation.ShallowReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "timesheet")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TimesheetEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "notes", nullable = true)
    private String notes;

    @Basic
    @Column(name = "periodendingdate", nullable = false)
    private LocalDate periodendingdate;

    @Basic
    @Column(name = "periodstartingdate", nullable = false)
    private LocalDate periodstartingdate;

    @ShallowReference
    @OneToMany(mappedBy = "timesheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimesheetdetailsEntity> timesheetdetailsSet = new HashSet<TimesheetdetailsEntity>();

    public void addTimesheetdetails(TimesheetdetailsEntity timesheetdetails) {
        timesheetdetailsSet.add(timesheetdetails);
        timesheetdetails.setTimesheet(this);
    }

    public void removeTimesheetdetails(TimesheetdetailsEntity timesheetdetails) {
        timesheetdetailsSet.remove(timesheetdetails);
        timesheetdetails.setTimesheet(null);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timesheetstatusid")
    private TimesheetstatusEntity timesheetstatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private UsersEntity users;
}

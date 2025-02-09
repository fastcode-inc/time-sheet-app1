package com.fastcode.timesheetapp1.domain.core.customer;

import com.fastcode.timesheetapp1.domain.core.abstractentity.AbstractEntity;
import com.fastcode.timesheetapp1.domain.core.project.ProjectEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javers.core.metamodel.annotation.ShallowReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CustomerEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid", nullable = false)
    private Long customerid;

    @Basic
    @Column(name = "description", nullable = true)
    private String description;

    @Basic
    @Column(name = "isactive", nullable = false)
    private Boolean isactive;

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @ShallowReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectEntity> projectsSet = new HashSet<ProjectEntity>();

    public void addProjects(ProjectEntity projects) {
        projectsSet.add(projects);
        projects.setCustomer(this);
    }

    public void removeProjects(ProjectEntity projects) {
        projectsSet.remove(projects);
        projects.setCustomer(null);
    }
}

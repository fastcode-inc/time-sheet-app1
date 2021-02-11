package com.fastcode.timesheetapp1.domain.core.authorization.users;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.*;
import com.fastcode.timesheetapp1.domain.core.usertask.UsertaskEntity;
import com.fastcode.timesheetapp1.domain.core.authorization.tokenverification.TokenverificationEntity;
import com.fastcode.timesheetapp1.domain.core.authorization.usersrole.UsersroleEntity;
import com.fastcode.timesheetapp1.domain.core.timesheet.TimesheetEntity;
import com.fastcode.timesheetapp1.domain.core.authorization.userspermission.UserspermissionEntity;
import com.fastcode.timesheetapp1.domain.core.authorization.userspreference.UserspreferenceEntity;
import com.fastcode.timesheetapp1.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.ShallowReference;

@Entity
@Table(name = "users")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UsersEntity extends AbstractEntity {

    @Basic
    @Column(name = "firstname", nullable = false,length =255)
    private String firstname;

    @Basic
    @Column(name = "trigger_name", nullable = true,length =200)
    private String triggerName;

    @Basic
    @Column(name = "isactive", nullable = false)
    private Boolean isactive;
    
    @Basic
    @Column(name = "emailaddress", nullable = false,length =255)
    private String emailaddress;

    @Basic
    @Column(name = "lastname", nullable = false,length =255)
    private String lastname;

    @Basic
    @Column(name = "password", nullable = false,length =255)
    private String password;

    @Basic
    @Column(name = "join_date", nullable = true)
    private LocalDate joinDate;

    @Basic
    @Column(name = "trigger_group", nullable = true,length =200)
    private String triggerGroup;

    @Basic
    @Column(name = "isemailconfirmed", nullable = false)
    private Boolean isemailconfirmed;
    
    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Basic
    @Column(name = "username", nullable = false,length =255)
    private String username;

	@ShallowReference
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserspermissionEntity> userspermissionsSet = new HashSet<UserspermissionEntity>();
    
    public void addUserspermissions(UserspermissionEntity userspermissions) {        
    	userspermissionsSet.add(userspermissions);
        userspermissions.setUsers(this);
    }
    public void removeUserspermissions(UserspermissionEntity userspermissions) {
        userspermissionsSet.remove(userspermissions);
        userspermissions.setUsers(null);
    }
    
	@ShallowReference
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsersroleEntity> usersrolesSet = new HashSet<UsersroleEntity>();
    
    public void addUsersroles(UsersroleEntity usersroles) {        
    	usersrolesSet.add(usersroles);
        usersroles.setUsers(this);
    }
    public void removeUsersroles(UsersroleEntity usersroles) {
        usersrolesSet.remove(usersroles);
        usersroles.setUsers(null);
    }
    
	@ShallowReference
    @OneToOne(mappedBy = "users", cascade=CascadeType.ALL, orphanRemoval = true)
    private UserspreferenceEntity userspreference;
    
    public void setUserspreference(UserspreferenceEntity userspreference) {
    	if (userspreference == null) {
            if (this.userspreference != null) {
                this.userspreference.setUsers(null);
            }
        }
        else {
            userspreference.setUsers(this);
        }
        this.userspreference = userspreference;
    }
    
	@ShallowReference
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimesheetEntity> timesheetsSet = new HashSet<TimesheetEntity>();
    
    public void addTimesheets(TimesheetEntity timesheets) {        
    	timesheetsSet.add(timesheets);
        timesheets.setUsers(this);
    }
    public void removeTimesheets(TimesheetEntity timesheets) {
        timesheetsSet.remove(timesheets);
        timesheets.setUsers(null);
    }
    
	@ShallowReference
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TokenverificationEntity> tokenverificationsSet = new HashSet<TokenverificationEntity>();
    
    public void addTokenverifications(TokenverificationEntity tokenverifications) {        
    	tokenverificationsSet.add(tokenverifications);
        tokenverifications.setUsers(this);
    }
    public void removeTokenverifications(TokenverificationEntity tokenverifications) {
        tokenverificationsSet.remove(tokenverifications);
        tokenverifications.setUsers(null);
    }
    
	@ShallowReference
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsertaskEntity> usertasksSet = new HashSet<UsertaskEntity>();
    
    public void addUsertasks(UsertaskEntity usertasks) {        
    	usertasksSet.add(usertasks);
        usertasks.setUsers(this);
    }
    public void removeUsertasks(UsertaskEntity usertasks) {
        usertasksSet.remove(usertasks);
        usertasks.setUsers(null);
    }
    

}




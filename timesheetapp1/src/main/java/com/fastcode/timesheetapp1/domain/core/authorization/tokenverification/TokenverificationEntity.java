package com.fastcode.timesheetapp1.domain.core.authorization.tokenverification;

import javax.persistence.*;
import java.time.*;
import java.util.Date;
import com.fastcode.timesheetapp1.domain.core.authorization.users.UsersEntity;
import com.fastcode.timesheetapp1.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tokenverification")
@IdClass(TokenverificationId.class)
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TokenverificationEntity extends AbstractEntity {

    @Basic
    @Column(name = "expiration_time", nullable = true)
    private Date expirationTime;

    @Basic
    @Column(name = "token", nullable = true,length =512)
    private String token;

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "token_type", nullable = false,length =256)
    private String tokenType;

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "users_id", nullable = false)
    private Long usersId;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "users_id", insertable=false, updatable=false)
    private UsersEntity users;


}




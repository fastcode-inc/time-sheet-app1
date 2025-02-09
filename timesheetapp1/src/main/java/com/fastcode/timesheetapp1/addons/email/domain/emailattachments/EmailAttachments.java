package com.fastcode.timesheetapp1.addons.email.domain.emailattachments;

import com.fastcode.timesheetapp1.addons.docmgmt.domain.file.FileEntity;
import com.fastcode.timesheetapp1.addons.email.domain.emailhistory.EmailHistory;
import com.fastcode.timesheetapp1.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "email_attachments")
@Getter
@Setter
public class EmailAttachments extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "file_id", nullable = false)
    private Long fileId;

    @Basic
    @Column(name = "email_id", nullable = false)
    private Long emailId;

    @Basic
    @Column(name = "type", nullable = false, length = 256)
    private String type;

    @ManyToOne
    @JoinColumn(name = "email_id", insertable = false, updatable = false)
    private EmailHistory emailHistory;

    @OneToOne
    @JoinColumn(name = "file_id", insertable = false, updatable = false)
    private FileEntity file;
}

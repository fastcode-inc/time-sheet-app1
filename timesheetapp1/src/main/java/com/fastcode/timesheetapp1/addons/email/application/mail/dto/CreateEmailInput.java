package com.fastcode.timesheetapp1.addons.email.application.mail.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateEmailInput {

    @NotNull(message = "To: should not be null")
    private String to;

    private String cc;
    private String bcc;
    private String subject;
    private String emailBody;
    private String replyTo;
    private Date sentDate;
    private Boolean isHtml;

    //List of fileIds for inline images and attachments
    private List<Long> inlineImages;
    private List<Long> attachments;
}

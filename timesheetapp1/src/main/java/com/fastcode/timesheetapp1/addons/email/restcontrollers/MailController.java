package com.fastcode.timesheetapp1.addons.email.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

import com.fastcode.timesheetapp1.addons.email.application.mail.dto.CreateEmailInput;
import com.fastcode.timesheetapp1.addons.email.application.mail.EmailService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
	private EmailService emailService;
	
	@PreAuthorize("hasAnyAuthority('SENDEMAIL')")
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity sendMail(@RequestBody @Valid CreateEmailInput email) throws IOException {

		if(email.getInlineImages() != null && !email.getIsHtml()) {
			throw new IOException(
	                String.format("isHtml flag should be true"));
		}
		
		emailService.sendMessage(email);
		return new ResponseEntity(HttpStatus.OK);
	} 
}


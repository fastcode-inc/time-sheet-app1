package com.fastcode.timesheetapp1.addons.scheduler.samplejobs;

import com.fastcode.timesheetapp1.addons.email.application.mail.IEmailService;
import com.fastcode.timesheetapp1.domain.core.timesheetdetails.TimesheetdetailsEntity;
import com.fastcode.timesheetapp1.domain.extended.timesheetdetails.ITimesheetdetailsRepositoryExtended;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class emailJob implements Job {

	@Autowired
	private IEmailService _emailService;

	@Autowired
	private ITimesheetdetailsRepositoryExtended _timesheetDetailsRepository;

	@Override
	public void execute(JobExecutionContext context) {

		String subject = "Reminder Email";
		String emailText = "This is a reminder email to fill out timesheet.\n";

		JobDataMap dataMap = context.getTrigger().getJobDataMap();
		String email = dataMap.getString("email");
		String userId = dataMap.getString("userId");

		List<TimesheetdetailsEntity> list = _timesheetDetailsRepository.findByWorkdateAndUserId(LocalDate.now(), Long.valueOf(userId));
		if(list.isEmpty()) {
			_emailService.sendEmail(_emailService.buildEmail(email, subject, emailText));

		}

	}

}

package com.fastcode.timesheetapp1.application.extended.timesheet;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fastcode.timesheetapp1.application.core.timesheet.TimesheetAppService;
import com.fastcode.timesheetapp1.application.core.timesheet.dto.CreateTimesheetInput;
import com.fastcode.timesheetapp1.application.core.timesheet.dto.CreateTimesheetOutput;
import com.fastcode.timesheetapp1.application.core.timesheet.dto.UpdateTimesheetOutput;
import com.fastcode.timesheetapp1.application.core.timesheetdetails.TimesheetdetailsAppService;
import com.fastcode.timesheetapp1.application.core.timesheetstatus.dto.UpdateTimesheetstatusOutput;
import com.fastcode.timesheetapp1.application.extended.authorization.users.IUsersAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.timesheet.dto.TimesheetOutput;
import com.fastcode.timesheetapp1.application.extended.timesheetdetails.ITimesheetdetailsAppServiceExtended;
import com.fastcode.timesheetapp1.application.extended.timesheetdetails.dto.TimesheetdetailsOutput;
import com.fastcode.timesheetapp1.domain.extended.timesheet.ITimesheetRepositoryExtended;
import com.fastcode.timesheetapp1.domain.extended.timesheetstatus.ITimesheetstatusRepositoryExtended;

import lombok.NonNull;

import com.fastcode.timesheetapp1.domain.core.authorization.users.UsersEntity;
import com.fastcode.timesheetapp1.domain.core.timesheet.TimesheetEntity;
import com.fastcode.timesheetapp1.domain.core.timesheetstatus.TimesheetstatusEntity;
import com.fastcode.timesheetapp1.domain.extended.authorization.users.IUsersRepositoryExtended;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;

@Service("timesheetAppServiceExtended")
public class TimesheetAppServiceExtended extends TimesheetAppService implements ITimesheetAppServiceExtended {

	public TimesheetAppServiceExtended(ITimesheetRepositoryExtended timesheetRepositoryExtended, 
			ITimesheetdetailsAppServiceExtended timesheetdetailsAppServiceExtended, IUsersAppServiceExtended usersAppServiceExtended,
			ITimesheetstatusRepositoryExtended timesheetstatusRepositoryExtended,IUsersRepositoryExtended usersRepositoryExtended,ITimesheetMapperExtended mapper,LoggingHelper logHelper) {

		super(timesheetRepositoryExtended,
		timesheetstatusRepositoryExtended,usersRepositoryExtended,mapper,logHelper);

		this.extendedMapper = mapper;
		this.timesheetRepositoryExtended = timesheetRepositoryExtended;
		this.timesheetdetailsAppServiceExtended = timesheetdetailsAppServiceExtended;
		this.usersAppServiceExtended = usersAppServiceExtended;
		this.timesheetstatusRepositoryExtended = timesheetstatusRepositoryExtended;
		}

	 	//Add your custom code here
		
	@Qualifier("ITimesheetdetailsMapperExtendedImpl")
	@NonNull protected final ITimesheetMapperExtended extendedMapper;
	
	@Qualifier("timesheetRepositoryExtended")
	@NonNull protected final ITimesheetRepositoryExtended timesheetRepositoryExtended;
	 
	@Qualifier("timesheetdetailsAppServiceExtended")
	@NonNull protected final ITimesheetdetailsAppServiceExtended timesheetdetailsAppServiceExtended;
 	
	@Qualifier("usersAppServiceExtended")
	@NonNull protected final IUsersAppServiceExtended usersAppServiceExtended;
	
	@Qualifier("timesheetstatusRepositoryExtended")
	@NonNull protected final ITimesheetstatusRepositoryExtended timesheetstatusRepositoryExtended;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public TimesheetOutput findTimesheetByDate(LocalDate date, Boolean includeDetails, Long userId) {

		TimesheetEntity foundTimesheet = timesheetRepositoryExtended.findByDate(date, userId);
		if (foundTimesheet == null)  
			return null; 
 	   
		TimesheetOutput timesheet = extendedMapper.timesheetEntityToTimesheetOutput(foundTimesheet);
		if(includeDetails) {
		List<TimesheetdetailsOutput> detailsList = timesheetdetailsAppServiceExtended.findByTimesheetId(foundTimesheet.getId());
		timesheet.setTimesheetdetailsList(detailsList);
		}
		return timesheet;
		
 	   // return mapper.timesheetEntityToFindTimesheetByIdOutput(foundTimesheet);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public UpdateTimesheetOutput updateTimesheetStatus(Long timesheetId, String status) {

		TimesheetstatusEntity timesheetstatus = timesheetstatusRepositoryExtended.findByStatusnameIgnoreCase(status);

		TimesheetEntity timesheet = timesheetRepositoryExtended.findById(timesheetId).orElse(null);
		if(!(timesheet.getTimesheetstatus().getStatusname().equalsIgnoreCase("Open") || 
				timesheet.getTimesheetstatus().getStatusname().equals("Rejected")) 
				&& status.equalsIgnoreCase("Submitted")) {
			return null;
		}
		timesheet.setTimesheetstatus(timesheetstatus);
		
		TimesheetEntity updatedTimesheet = _timesheetRepository.save(timesheet);
		return extendedMapper.timesheetEntityToUpdateTimesheetOutput(updatedTimesheet);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CreateTimesheetOutput create(CreateTimesheetInput input) {

		UsersEntity loggedInUser = usersAppServiceExtended.getUsers();
		input.setUserid(loggedInUser.getId());
		
		TimesheetstatusEntity timesheetstatus = timesheetstatusRepositoryExtended.findByStatusnameIgnoreCase("Open");
		input.setTimesheetstatusid(timesheetstatus.getId());
		
		TimesheetEntity timesheet = mapper.createTimesheetInputToTimesheetEntity(input);
		TimesheetstatusEntity foundTimesheetstatus = null;
		UsersEntity foundUsers = null;
	  	if(input.getTimesheetstatusid()!=null) {
			foundTimesheetstatus = _timesheetstatusRepository.findById(input.getTimesheetstatusid()).orElse(null);
			
			if(foundTimesheetstatus!=null) {
				foundTimesheetstatus.addTimesheets(timesheet);
				//timesheet.setTimesheetstatus(foundTimesheetstatus);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	  	if(input.getUserid()!=null) {
			foundUsers = _usersRepository.findById(input.getUserid()).orElse(null);
			
			if(foundUsers!=null) {
				foundUsers.addTimesheets(timesheet);
				//timesheet.setUsers(foundUsers);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}

		TimesheetEntity createdTimesheet = _timesheetRepository.save(timesheet);
		return mapper.timesheetEntityToCreateTimesheetOutput(createdTimesheet);
	}
 
}


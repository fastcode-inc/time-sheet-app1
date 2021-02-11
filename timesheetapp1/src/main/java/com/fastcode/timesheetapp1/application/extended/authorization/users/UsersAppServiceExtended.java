package com.fastcode.timesheetapp1.application.extended.authorization.users;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fastcode.timesheetapp1.addons.scheduler.application.trigger.ITriggerAppService;
import com.fastcode.timesheetapp1.addons.scheduler.application.trigger.dto.CreateTriggerInput;
import com.fastcode.timesheetapp1.application.core.authorization.users.UsersAppService;
import com.fastcode.timesheetapp1.application.core.authorization.users.dto.UpdateUsersInput;
import com.fastcode.timesheetapp1.application.core.authorization.users.dto.UpdateUsersOutput;
import com.fastcode.timesheetapp1.domain.extended.authorization.users.IUsersRepositoryExtended;

import lombok.NonNull;

import com.fastcode.timesheetapp1.domain.core.authorization.users.UsersEntity;
import com.fastcode.timesheetapp1.domain.core.authorization.userspreference.IUserspreferenceRepository;
import com.fastcode.timesheetapp1.domain.core.authorization.userspreference.UserspreferenceEntity;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;

@Service("usersAppServiceExtended")
public class UsersAppServiceExtended extends UsersAppService implements IUsersAppServiceExtended {

	public UsersAppServiceExtended(IUsersRepositoryExtended usersRepositoryExtended,
			IUserspreferenceRepository userspreferenceRepository,IUsersMapperExtended mapper,LoggingHelper logHelper, ITriggerAppService triggerApp) {

		super(usersRepositoryExtended,
				userspreferenceRepository,mapper,logHelper);

		this.triggerApp = triggerApp;
	}
	
	@NonNull protected final ITriggerAppService triggerApp;

	//Add your custom code here
	@Transactional(propagation = Propagation.REQUIRED)
	public UpdateUsersOutput associateTriggerWithUser(UsersEntity users,CreateTriggerInput trigger) {

		users.setTriggerGroup(trigger.getTriggerGroup());
		users.setTriggerName(trigger.getTriggerName());

		//		UsersEntity existing = _usersRepository.findById(usersId).get();
		//
		//		UsersEntity users = mapper.updateUsersInputToUsersEntity(input);
		//		users.setTimesheetsSet(existing.getTimesheetsSet());
		//		users.setTokenverificationsSet(existing.getTokenverificationsSet());
		//		users.setUserspermissionsSet(existing.getUserspermissionsSet());
		//		users.setUsersrolesSet(existing.getUsersrolesSet());
		//		users.setUsertasksSet(existing.getUsertasksSet());

		UsersEntity updatedUsers = _usersRepository.save(users);
		return mapper.usersEntityToUpdateUsersOutput(updatedUsers);
	}

	@Override
	public void delete(Long usersId) {

		UsersEntity existing = _usersRepository.findById(usersId).orElse(null); 

		UserspreferenceEntity userspreference = _userspreferenceRepository.findById(usersId).orElse(null);
		_userspreferenceRepository.delete(userspreference);
		
		try {
			triggerApp.cancelTrigger(existing.getTriggerName(), existing.getTriggerGroup());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		_usersRepository.delete(existing);
	}

}


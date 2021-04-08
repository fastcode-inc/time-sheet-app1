package com.fastcode.timesheetapp1.domain.extended.timesheet;

import com.fastcode.timesheetapp1.domain.core.timesheet.ITimesheetRepository;
import com.fastcode.timesheetapp1.domain.core.timesheet.TimesheetEntity;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@JaversSpringDataAuditable
@Repository("timesheetRepositoryExtended")
public interface ITimesheetRepositoryExtended extends ITimesheetRepository {

	//Add your custom code here
	@Query("select t from TimesheetEntity t where t.periodstartingdate <= ?1 AND t.periodendingdate >= ?1 AND t.users.id=?2")
	TimesheetEntity findByDate(LocalDate date, Long userId);

}


import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder } from '@angular/forms';
import { UsertaskExtendedService } from 'src/app/extended/entities/usertask';
import { of } from 'rxjs';
import { ITimesheet, TimesheetService } from 'src/app/entities/timesheet';
import { TimesheetExtendedService } from 'src/app/extended/entities/timesheet';
import * as moment from 'moment';
import { TimeofftypeExtendedService } from 'src/app/extended/entities/timeofftype';
import { ITimeofftype } from 'src/app/entities/timeofftype';

@Component({
  selector: 'app-timesheet',
  templateUrl: './timesheet.component.html',
  styleUrls: ['./timesheet.component.scss']
})
export class TimesheetComponent implements OnInit {
  title:string='Timesheet';
  parentUrl:string='timesheet';

  loading = false;
  customers = [];
  projectsMap = {};
  tasksMap = {};
  notesindex: any;
  totalhours: any = 0;
  timesheetDate: Date;
  daysheet = [];
  timesheet: ITimesheet;
  timeOffTypes: ITimeofftype[];
  editable = true;

	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public usertaskService: UsertaskExtendedService,
		public timesheetService: TimesheetExtendedService,
		public timeofftypeService: TimeofftypeExtendedService,
	) { }

	ngOnInit() {
    this.timesheetDate = new Date();
    let timesheet = {
      periodstartingdate: this.getStartDateOfPeriod().getTime(),
      periodendingdate: this.getEndingDateOfPeriod().getTime(),
    }
    console.log(timesheet)
    this.getTimesheet();
    this.getTimesheetDetails();
    this.getUserTasks();
    this.getTimeOff();
  }

  getTimesheet() {
    this.timesheet = null;
    this.daysheet = [];

    this.timesheetService.getByDate(this.timesheetDate).subscribe(res => {
      this.timesheet = res;
      this.editable = this.timesheet.timesheetstatusDescriptiveField === 'Open' ||
        this.timesheet.timesheetstatusDescriptiveField === 'Rejected';
    }, err => {this.editable = true;});
  }

  getTimeOff() {
    this.timeofftypeService.getAll().subscribe(res => {
      this.timeOffTypes = res;
    });
  }

  getTimesheetDetails() {
    this.timesheetService.getTimesheetDetails(this.timesheetDate).subscribe(res => {
      this.daysheet = res;
      this.totalDayHours();
    })
  }

  dateChanged(event) {
    this.getTimesheet();
    this.getTimesheetDetails();
  }

  getUserTasks() {
    
    this.usertaskService.getTasks().subscribe(res => {
      res.forEach(task => {
        var i = this.customers.findIndex(customer => customer.id == task.customerid);
        if(i <= -1){
          this.customers.push({id: task.customerid, name: task.customerDescriptiveField});
        }

        if(!(task.customerid in this.projectsMap)) {
          this.projectsMap[task.customerid] = [];
        }
        if(this.projectsMap[task.customerid].findIndex(project => project.id === task.projectid) === -1) {
          this.projectsMap[task.customerid].push({id: task.projectid, name: task.projectDescriptiveField});
        }
        
        if(!(task.projectid in this.tasksMap)) {
          this.tasksMap[task.projectid] = [];
        }
        if(this.tasksMap[task.projectid].findIndex(t => t.id === task.taskid) === -1) {
          this.tasksMap[task.projectid].push({id: task.taskid, name: task.taskDescriptiveField});
        }
      })
    })
  }

  nextDate() {
    var day = new Date(this.timesheetDate);
    var nextDay = new Date( this.timesheetDate);
    nextDay.setDate(day.getDate() + 1);
    this.timesheetDate = nextDay;
    
    this.getTimesheet();
    this.getTimesheetDetails();
  }

  backDate() {
    var day = new Date(this.timesheetDate);
    var backDay = new Date( this.timesheetDate);
    backDay.setDate(day.getDate() - 1);
    this.timesheetDate = backDay;

    this.getTimesheet();
    this.getTimesheetDetails();
  }

  addTimesheetDetails() {
    let timesheetDetails = {
      customerid: null,
      projectid: null,
      taskid: null,
      notes: null,
      hours: null,
      timesheetid: this.timesheet ? this.timesheet.id : undefined,
      workdate: this.timesheetService.getFormattedDate(this.timesheetDate, 'yyyy-mm-dd')
    };
    this.daysheet.push(timesheetDetails);
  }

  addTimeOff() {
    let timeoff = {
      type: 'timeoff',
      notes: '',
      hours: 0,
      timesheetid: this.timesheet ? this.timesheet.id : undefined,
      workdate: this.timesheetDate
    };
    this.daysheet.push(timeoff);
  }

  deleteRow(idx) {
    this.totalDayHours();
    this.daysheet.splice(idx, 1);
  }

  addNotes(idx) {
    if (this.notesindex != idx) {
      this.notesindex = idx;
    } else {
      this.notesindex = -1;
    }
  }

  totalDayHours() {
    this.totalhours = 0;
    for (let i=0; i < this.daysheet.length; i++ ) {
      this.totalhours = this.totalhours + parseInt(this.daysheet[i].hours);
    }
  }

  save() {
    this.loading = true;
    if(this.timesheet) {
      this.timesheetService.createTimesheetDetails(this.daysheet).subscribe(res => {
        this.loading = false;
        this.getTimesheet();
        this.getTimesheetDetails();
        console.log(res)
      });
    } else {
      let timesheet = {
        periodstartingdate: this.timesheetService.getFormattedDate(this.getStartDateOfPeriod(), 'yyyy-mm-dd'),
        periodendingdate: this.timesheetService.getFormattedDate(this.getEndingDateOfPeriod(), 'yyyy-mm-dd'),
      }
      this.timesheetService.create(timesheet).subscribe(res => {
        this.daysheet = this.daysheet.map(obj=> ({ ...obj, timesheetid: res.id }));
        this.timesheetService.createTimesheetDetails(this.daysheet).subscribe(res => {
          this.loading = false;
          this.getTimesheet();
          this.getTimesheetDetails();
        })
      });
    }
  }

  getStartDateOfPeriod() {
    var firstDate = new Date(this.timesheetDate.getFullYear(), this.timesheetDate.getMonth(), 1);
    if (this.timesheetDate.getDate() >= 15) {
      firstDate.setDate(16);
    }
    // converting into utc
    return firstDate;
  }

  getEndingDateOfPeriod() {
    var lastDate = new Date(this.timesheetDate.getFullYear(), this.timesheetDate.getMonth() + 1, 0);
    if (this.timesheetDate.getDate() < 15) {
      lastDate = new Date(this.timesheetDate.getFullYear(), this.timesheetDate.getMonth(), 15);
    }
    return lastDate;
  }

}


import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TimesheetService } from 'src/app/entities/timesheet/timesheet.service';
import { ITimesheetdetails } from 'src/app/entities/timesheetdetails';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ITimesheet } from 'src/app/entities/timesheet';
import * as Moment from 'moment';
import { extendMoment } from 'moment-range';
const moment = extendMoment(Moment);

@Injectable({
  providedIn: 'root'
})
export class TimesheetExtendedService extends TimesheetService {
	constructor(protected httpclient: HttpClient) { 
    super(httpclient);
    this.url += '/extended';
  }

  getByDate(workDate: Date, includeDetails?: boolean, userId?: number): Observable<any> {
    let params: any = { 
      date: moment(workDate).format('DD-MM-YYYY'),
      includeDetails: includeDetails? 'true' : 'false'
    }
    if(userId) {
      params['userId'] = userId.toString();
    }
    return this.http.get(`${this.url}/getTimesheet`, {params: params}).pipe(catchError(this.handleError));
  }

  setTimesheetStatus(timesheetid:number, input) {
    return this.http.put(`${this.url}/${timesheetid}/updateTimesheetStatus`, input).pipe(catchError(this.handleError));
  }

}

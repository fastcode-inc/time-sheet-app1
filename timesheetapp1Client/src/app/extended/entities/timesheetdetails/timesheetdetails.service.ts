
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TimesheetdetailsService } from 'src/app/entities/timesheetdetails/timesheetdetails.service';
import { ITimesheetdetails } from 'src/app/entities/timesheetdetails';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/internal/operators/catchError';
import * as Moment from 'moment';
import { extendMoment } from 'moment-range';
const moment = extendMoment(Moment);

@Injectable({
  providedIn: 'root'
})
export class TimesheetdetailsExtendedService extends TimesheetdetailsService {
	constructor(protected httpclient: HttpClient) { 
    super(httpclient);
    this.url += '/extended';
  }

    /**
   * Calls api to create given item.
   * @param item
   * @returns Observable of created entity object.
   */
  createTimesheetDetails(items: ITimesheetdetails[]): Observable<any> {
    return this.http.post(this.url + '/timesheetdetails', items).pipe(catchError(this.handleError));
  }

  /**
   * Calls api to fetch list of timesheet details.
   * @param workDate
   * @returns Observable of created entity object.
   */
  getTimesheetDetails(workDate: Date): Observable<ITimesheetdetails[]> {
    let date = moment(workDate).format('DD-MM-YYYY')
    return this.http.get<ITimesheetdetails[]>(`${this.url}/timesheetdetails/?workDate=${date}`).pipe(catchError(this.handleError));
  }
}

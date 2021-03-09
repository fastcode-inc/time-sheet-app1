
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UsersService } from 'src/app/admin/user-management/users/users.service';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class UsersExtendedService extends UsersService {
	constructor(protected httpclient: HttpClient) { 
    super(httpclient);
    this.url += '/extended';
  }

  public setReminder(input): Observable<any> {
    return this.httpclient
      .post(this.url + '/reminder', input).pipe(catchError(this.handleError));
  }

  public getReminderDetails(): Observable<any> {
    return this.httpclient
      .get(this.url + '/getReminderDetails').pipe(catchError(this.handleError));
  }

}

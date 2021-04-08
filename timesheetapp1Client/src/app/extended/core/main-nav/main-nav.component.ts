import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';

import { AuthenticationService } from 'src/app/core/authentication.service';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';
import { UsersExtendedService } from 'src/app/extended/admin/user-management/users/index';

import { Globals } from 'src/app/common/shared';
import { MainNavComponent } from 'src/app/core/main-nav/main-nav.component';

@Component({
	selector: 'app-main-nav',
	templateUrl: './main-nav.component.html',
	styleUrls: ['./main-nav.component.scss', './main-nav-mixin.component.scss']
})
export class MainNavExtendedComponent extends MainNavComponent {	
	
	constructor(
		public router: Router,
		public translate: TranslateService,
		public globals: Globals,


		public authenticationService: AuthenticationService,
		public globalPermissionService: GlobalPermissionService,
		public usersExtendedService: UsersExtendedService,
	) {
		super(router, translate, globals,


		authenticationService,
		globalPermissionService,
		usersExtendedService
		)

	}

	setPermissions() {
		super.setPermissions();
		this.permissions['AUTH_ENTITIES'] = false;
		let perms = ["SHOW_ENTITIES", "SET_REMINDER", "CREATE_TIMESHEETDETAILS_BULK", "READ_TIMESHEET_WITH_DETAILS"]
		this.permissions['TIMESHEETSTATUSENTITY_UPDATE'] = this.globalPermissionService.hasPermissionOnEntity('TIMESHEETSTATUS', 'update');
		this.permissions['TIMESHEETENTITY_CREATE'] = this.globalPermissionService.hasPermissionOnEntity('TIMESHEET', 'create');
		this.authEntityList.forEach(entity => {
			if(this.permissions[entity]) {
				this.permissions['AUTH_ENTITIES'] = true;
			}
		});
		perms.forEach( perm => {
			this.permissions[perm] = this.globalPermissionService.hasPermission(perm);
		});
	}
}
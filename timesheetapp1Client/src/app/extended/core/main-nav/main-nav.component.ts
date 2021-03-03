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

		this.permissions['TIMESHEETSTATUSENTITY_UPDATE'] = this.globalPermissionService.hasPermissionOnEntity('TIMESHEETSTATUS', 'update');
		this.permissions['showEntities'] = this.globalPermissionService.hasPermission('showEntities');
		this.authenticationService.permissionsChange.subscribe(() => {
			this.permissions['showEntities'] = this.globalPermissionService.hasPermission('showEntities');
			this.permissions['TIMESHEETSTATUSENTITY_UPDATE'] = this.globalPermissionService.hasPermissionOnEntity('TIMESHEETSTATUS', 'update')
		});
	}
}
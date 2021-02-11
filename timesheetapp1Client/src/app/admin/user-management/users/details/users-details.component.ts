import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder, Validators} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { UsersService } from '../users.service';
import { IUsers } from '../iusers';
import { BaseDetailsComponent, Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';


@Component({
  selector: 'app-users-details',
  templateUrl: './users-details.component.html',
  styleUrls: ['./users-details.component.scss']
})
export class UsersDetailsComponent extends BaseDetailsComponent<IUsers> implements OnInit {
	title = 'Users';
	parentUrl = 'users';
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public global: Globals,
		public usersService: UsersService,
		public pickerDialogService: PickerDialogService,
		public errorService: ErrorService,
		public globalPermissionService: GlobalPermissionService,
	) {
		super(formBuilder, router, route, dialog, global, pickerDialogService, usersService, errorService);
  }

	ngOnInit() {
		this.entityName = 'Users';
		this.setAssociations();
		super.ngOnInit();
		this.setForm();
    	this.getItem();
	}
  
  setForm(){
    this.itemForm = this.formBuilder.group({
      emailaddress: ['', Validators.required],
      firstname: ['', Validators.required],
      id: [{value: '', disabled: true}, Validators.required],
      isactive: [false, Validators.required],
      isemailconfirmed: [false, Validators.required],
      joinDate: [''],
      lastname: ['', Validators.required],
      triggerGroup: [''],
      triggerName: [''],
      username: ['', Validators.required],
      
    });
    
    this.fields = [
			
        {
		  name: 'emailaddress',
		  label: 'emailaddress',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
        {
		  name: 'firstname',
		  label: 'firstname',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
			
        {
		  name: 'isactive',
		  label: 'isactive',
		  isRequired: true,
		  isAutoGenerated: false,
          type: 'boolean',
	    },
			
        {
		  name: 'isemailconfirmed',
		  label: 'isemailconfirmed',
		  isRequired: true,
		  isAutoGenerated: false,
          type: 'boolean',
	    },
			
        {
		  name: 'joinDate',
		  label: 'join Date',
		  isRequired: false,
		  isAutoGenerated: false,
		  type: 'date',
	    },
			
        {
		  name: 'lastname',
		  label: 'lastname',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
			
        {
		  name: 'triggerGroup',
		  label: 'trigger Group',
		  isRequired: false,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
        {
		  name: 'triggerName',
		  label: 'trigger Name',
		  isRequired: false,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
        {
		  name: 'username',
		  label: 'username',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'string',
	    },
      ];
      
  }
  
  onItemFetched(item: IUsers) {
    this.item = item;
    this.itemForm.patchValue(item);
    this.itemForm.get('joinDate').setValue(item.joinDate? new Date(item.joinDate): null);
  }
  
  setAssociations(){
    this.associations = [
      {
        column: [
	        {
	          key: 'userid',
	          value: undefined,
	          referencedkey: 'id'
			},
		],
		isParent: true,
		table: 'usertask',
		type: 'OneToMany',
		label: 'usertasks',
		},
			{
				column: [
					{
						key: 'usersId',
						value: undefined,
						referencedkey: 'id'
					},
				],
				isParent: true,
				table: 'userspermission',
				type: 'OneToMany',
				label: 'Userpermissions',
			},
			{
		        column: [
					{
						key: 'usersId',
						value: undefined,
						referencedkey: 'id'
					},
		        ],
		        isParent: true,
		        table: 'usersrole',
		        type: 'OneToMany',
				label: 'Userroles',
		    },
		];
		
		this.childAssociations = this.associations.filter(association => {
			return (association.isParent);
		});

		this.parentAssociations = this.associations.filter(association => {
			return (!association.isParent);
		});
	}
	
	onSubmit() {
		let users = this.itemForm.getRawValue();
		super.onSubmit(users);
		
	}
}

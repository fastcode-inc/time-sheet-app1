import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { UsersService } from '../users.service';
import { IUsers } from '../iusers';
import { Globals, BaseNewComponent, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';

@Component({
  selector: 'app-users-new',
  templateUrl: './users-new.component.html',
  styleUrls: ['./users-new.component.scss'],
})
export class UsersNewComponent extends BaseNewComponent<IUsers> implements OnInit {
  title: string = 'New Users';
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<UsersNewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public global: Globals,
    public pickerDialogService: PickerDialogService,
    public usersService: UsersService,
    public errorService: ErrorService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(formBuilder, router, route, dialog, dialogRef, data, global, pickerDialogService, usersService, errorService);
  }

  ngOnInit() {
    this.entityName = 'Users';
    this.setAssociations();
    super.ngOnInit();
    this.setForm();
    this.checkPassedData();
  }

  setForm() {
    this.itemForm = this.formBuilder.group({
      emailaddress: ['', Validators.required],
      firstname: ['', Validators.required],
      isactive: [false, Validators.required],
      joinDate: [''],
      lastname: ['', Validators.required],
      password: ['', Validators.required],
      username: ['', Validators.required],
      confirmPassword: ['', Validators.required],
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
        name: 'password',
        label: 'password',
        isRequired: true,
        isAutoGenerated: false,
        type: 'password',
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

  setAssociations() {
    this.associations = [];
    this.parentAssociations = this.associations.filter((association) => {
      return !association.isParent;
    });
  }

  onSubmit() {
    let users = this.itemForm.getRawValue();
    super.onSubmit(users);
  }
}

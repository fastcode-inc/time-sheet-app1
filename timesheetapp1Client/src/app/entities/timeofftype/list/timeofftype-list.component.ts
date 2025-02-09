import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialog } from '@angular/material';

import { ITimeofftype } from '../itimeofftype';
import { TimeofftypeService } from '../timeofftype.service';
import { Router, ActivatedRoute } from '@angular/router';
import { TimeofftypeNewComponent } from '../new/timeofftype-new.component';
import { BaseListComponent, Globals, listColumnType, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';

@Component({
  selector: 'app-timeofftype-list',
  templateUrl: './timeofftype-list.component.html',
  styleUrls: ['./timeofftype-list.component.scss'],
})
export class TimeofftypeListComponent extends BaseListComponent<ITimeofftype> implements OnInit {
  title = 'Timeofftype';
  constructor(
    public router: Router,
    public route: ActivatedRoute,
    public global: Globals,
    public dialog: MatDialog,
    public changeDetectorRefs: ChangeDetectorRef,
    public pickerDialogService: PickerDialogService,
    public timeofftypeService: TimeofftypeService,
    public errorService: ErrorService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(router, route, dialog, global, changeDetectorRefs, pickerDialogService, timeofftypeService, errorService);
  }

  ngOnInit() {
    this.entityName = 'Timeofftype';
    this.setAssociation();
    this.setColumns();
    this.primaryKeys = ['id'];
    super.ngOnInit();
  }

  setAssociation() {
    this.associations = [];
  }

  setColumns() {
    this.columns = [
      {
        column: 'typename',
        searchColumn: 'typename',
        label: 'typename',
        sort: true,
        filter: true,
        type: listColumnType.String,
      },
      {
        column: 'actions',
        label: 'Actions',
        sort: false,
        filter: false,
        type: listColumnType.String,
      },
    ];
    this.selectedColumns = this.columns;
    this.displayedColumns = this.columns.map((obj) => {
      return obj.column;
    });
  }
  addNew(comp) {
    if (!comp) {
      comp = TimeofftypeNewComponent;
    }
    super.addNew(comp);
  }
}

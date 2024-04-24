import {Component, OnInit, TemplateRef} from '@angular/core';
import {BehaviorSubject, firstValueFrom, map, Observable, of, startWith} from "rxjs";
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";

import {
  faTrash,
  faEdit,
} from '@fortawesome/free-solid-svg-icons';

import {AppState} from "../../../../interfaces/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {DataState} from "../../../../enums/data-state";
import {OrganizationService} from "../../../../services/models/politics/organization/organization.service";
import {Organization} from "../../../../interfaces/models/politics/organization";

@Component({
  selector: 'app-organization',
  templateUrl: './organization.component.html',
  styleUrl: './organization.component.css'
})
export class OrganizationComponent implements OnInit {
  currentPage:number  = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  organizations: Organization[];
  checkedOrganizations: Organization[];
  selectedOrganization: Organization;

  isUpdated: boolean = false;
  isClicked: boolean = false;
  isTableShown: boolean = false;
  isMasterSelected: boolean = false;

  faTrash = faTrash;
  faEdit = faEdit;
  headers = [
    {
      key: 'name',
      value: 'Organization Name',
    }
  ];

  constructor(private organizationService: OrganizationService) {
  }

  ngOnInit(): void {
    this.getPaginatedOrganizations(this.currentPage, this.tableSize);
  }

  getPaginatedOrganizations(pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.organizationService.getPaginatedOrganizations$(pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.dataSubject.next(result); //stores result in dataSubject to be used in another method or later
          this.isTableShown = true;
          return {
            dataState: DataState.LOADED,
            appData: result,
          };
        }),
        startWith({
          dataState: DataState.LOADING,
          // appData: null
        }),
        catchError((caughtError: string) => {
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
  }

  getOrganizationByID(organizationID: number) {
    this.organizationService.getOrganizationByID(organizationID).subscribe(result => {
      this.selectedOrganization = result.data.datumRetrieved;
    })
  }

  saveOrganization(organizationForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.organizationService
      .saveOrganization$(organizationForm.value) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.organizations = result.data.dataRetrieved;
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: [
                  result.data.dataSaved,
                  ...this.dataSubject.value.data.dataRetrieved,
                ],
              },
            });
          this.isClicked = false;
          this.isTableShown = true;
          this.isLoading.next(false);
          return {
            dataState: DataState.LOADED,
            appData: this.dataSubject.value,
          };
        }),
        startWith({
          dataState: DataState.LOADED,
          appData: this.dataSubject.value, //begin with pre-loaded data
        }),
        catchError((caughtError: string) => {
          this.isLoading.next(false);
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
  }

  deleteOrganization(organization: Organization) {
    this.appState$ = this.organizationService
      .deleteOrganization$(organization.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((o) =>
                  o.id !== organization.id //delete the record that matches d.id === day.id
                )
              }
            }
          );
          return {
            dataState: DataState.LOADED,
            appData: this.dataSubject.value,
          };
        }),
        startWith({
          dataState: DataState.LOADED,
          appData: this.dataSubject.value, //begin with pre-loaded data
        }),
        catchError((caughtError: string) => {
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
  }

  modifyOrganization(organization: Organization) {
    this.isLoading.next(true);
    this.appState$ = this.organizationService.modifyOrganization$(organization.id, organization).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(person => person.id === result.data.dataUpdated.id);
        this.dataSubject.value.data.dataRetrieved[index] = result.data.dataUpdated;
        this.isUpdated = false;
        this.isTableShown = true;
        this.isLoading.next(false);
        return {
          dataState: DataState.LOADED,
          appData: this.dataSubject.value
        };
      }),
      startWith({
        dataState: DataState.LOADED,
        appData: this.dataSubject.value
      }),
      catchError((caughtError: string) => {
        return of({
          dataState: DataState.ERROR,
          error: caughtError,
        });
      })
    );
  }

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedOrganizations(this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedOrganizations(this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for(let organization of this.organizations) {
      organization.isSelected = this.isMasterSelected;
    }
    this.getCheckedOrganization();
  }

  isAllSelected() {
    this.isMasterSelected = this.organizations.every(organization => organization.isSelected);
    this.getCheckedOrganization();
  }

  getCheckedOrganization() {
    this.checkedOrganizations = [];
    for (let checkedOrganization of this.organizations) {
      if (checkedOrganization.isSelected) {
        this.checkedOrganizations.push(checkedOrganization);
      }
    }
  }
}

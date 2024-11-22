import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Organization} from "../../../../../interfaces/models/politics/organization";
import {map, Observable, of} from "rxjs";
import {AppState} from "../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {DataState} from "../../../../../enums/data-state";
import {OrganizationService} from "../../../../../services/models/politics/organization/organization.service";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-organization-display-edit',
  templateUrl: './organization-display-edit.component.html',
  styleUrl: './organization-display-edit.component.css'
})
export class OrganizationDisplayEditComponent implements OnInit {
  @Output() isEditing = new EventEmitter<boolean>();
  @Input() organization: Organization;

  selectedOrganization: Organization;

  organizationID: string;
  organizationName: string;

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;

  constructor(private organizationService: OrganizationService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.organizationID = result.get("id");
        this.organizationName = result.get("name");
      }
    )
    await this.getOrganizationByID(+this.organizationID);
  }

  async getOrganizationByID(organizationID: number) {
    const result = await this.organizationService.getOrganizationByID(organizationID).toPromise();
    this.selectedOrganization = result.data.datumRetrieved;
    console.log(this.selectedOrganization);
  }

  onSubmit(organization: Organization) {
    console.log(organization);
    // Send the request to update the religion details
    this.organizationService.updateOrganization$(this.organization.id, organization)
      .pipe(
        map((response) => {
          console.log('Organization modified:', response);
          // Update the religion object locally after modification
          // Assuming religion has an ID and fields you want to update
          const updatedOrganization = response.data.dataUpdated;
          Object.assign(organization, updatedOrganization);  // Merge the updated details
          this.onClose(false);
          this.organizationService.notifyUpdate(updatedOrganization); // Notify
          this.router.navigateByUrl(`/organizations/${+this.organizationID}/${updatedOrganization.name}`);
        }),
        catchError((caughtError: string) => {
          console.error('Error modifying organization:', caughtError);
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      )
      .subscribe(
        (state) => {
          // Handle the new state if you are using a store, subject, or state management
          console.log('State after organization modification:', state);
        }
      );
  }

  onClose(editable: boolean) {
    this.isEditing.emit(editable);
  }
}

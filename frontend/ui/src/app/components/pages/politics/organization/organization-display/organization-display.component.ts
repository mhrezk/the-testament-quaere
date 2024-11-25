import {Component, OnDestroy, OnInit} from '@angular/core';
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {Observable, Subscription} from "rxjs";
import {AppState} from "../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {DataState} from "../../../../../enums/data-state";
import {OrganizationService} from "../../../../../services/models/politics/organization/organization.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Organization} from "../../../../../interfaces/models/politics/organization";

@Component({
  selector: 'app-organization-display',
  templateUrl: './organization-display.component.html',
  styleUrl: './organization-display.component.css'
})
export class OrganizationDisplayComponent implements OnInit, OnDestroy {
  isEditing: boolean = false;
  isDescription: boolean = false;

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  selectedOrganization: Organization;
  organizationID: string;
  organizationName: string;

  private updateSubscription: Subscription;

  constructor(private organizationService: OrganizationService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.organizationName = result.get("name");
        this.organizationID = result.get("id");
      }
    )

    this.updateSubscription = this.organizationService.organizationUpdated$.subscribe(
      (updatedOrganization) => {
        this.selectedOrganization = updatedOrganization; // Update local state
      }
    );

    if(!this.selectedOrganization) {
      await this.getOrganizationByID(+this.organizationID);
    }
  }

  ngOnDestroy() {
    this.updateSubscription.unsubscribe();
  }

  async getOrganizationByID(organizationID: number) {
    const result = await this.organizationService.getOrganizationByID(organizationID).toPromise();
    this.selectedOrganization = result.data.datumRetrieved;
    console.log(this.selectedOrganization);
  }

  deleteOrganization(organizationID: number) {
    this.organizationService.deleteOrganization$(organizationID).subscribe((result) => {
      console.log(result);
      this.routeToOrganizations();
    });
  }

  closeEditing(editable: boolean) {
    this.isEditing = editable;
  }

  closeDescriptionEditing(editable: boolean) {
    this.isDescription = editable;
  }

  routeToOrganizations() {
    this.router.navigateByUrl(`/organizations`);
  }
}

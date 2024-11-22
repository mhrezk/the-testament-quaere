import {Component, EventEmitter, Input, Output} from '@angular/core';
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {Organization} from "../../../../../interfaces/models/politics/organization";
import {OrganizationService} from "../../../../../services/models/politics/organization/organization.service";

@Component({
  selector: 'app-organization-description',
  templateUrl: './organization-description.component.html',
  styleUrl: './organization-description.component.css'
})
export class OrganizationDescriptionComponent {
  @Input() organization: Organization;
  @Output() isDescription = new EventEmitter<boolean>();

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  constructor(private organizationService: OrganizationService) {}

  isDescriptionEdited(editable: boolean) {
    this.isDescription.emit(editable);
  }

  updateDescription() {
    this.organizationService.updateOrganization$(this.organization.id, this.organization).subscribe();
    this.isDescription.emit(false);
  }
}

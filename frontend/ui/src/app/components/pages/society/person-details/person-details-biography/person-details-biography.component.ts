import {Component, EventEmitter, Input, Output} from '@angular/core';
import {PersonDetails} from "../../../../../interfaces/models/society/person-details";
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {PersonDetailsService} from "../../../../../services/models/society/person-details/person-details.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-person-details-biography',
  templateUrl: './person-details-biography.component.html',
  styleUrl: './person-details-biography.component.css'
})
export class PersonDetailsBiographyComponent {
  @Input() personDetails: PersonDetails;
  @Output() isBiography = new EventEmitter<boolean>();

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  constructor(private personDetailsService: PersonDetailsService) {}

  isBiographyEdited(editable: boolean) {
    this.isBiography.emit(editable);
  }

  updateBiography() {
    this.personDetailsService.updatePersonDetails(this.personDetails.id, this.personDetails).subscribe();
    this.isBiography.emit(false);
  }
}

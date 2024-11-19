import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NationDetails} from "../../../../../interfaces/models/places/nation-details";
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {Religion} from "../../../../../interfaces/models/dogma/religion";
import {ReligionService} from "../../../../../services/models/dogma/religion/religion.service";

@Component({
  selector: 'app-religion-description',
  templateUrl: './religion-description.component.html',
  styleUrl: './religion-description.component.css'
})
export class ReligionDescriptionComponent {
  @Input() religion: Religion;
  @Output() isDescription = new EventEmitter<boolean>();

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  constructor(private religionService: ReligionService) {

  }

  isDescriptionEdited(editable: boolean) {
    this.isDescription.emit(editable);
  }

  updateDescription() {
    this.religionService.updateReligion$(this.religion.id, this.religion).subscribe();
    this.isDescription.emit(false);
  }
}

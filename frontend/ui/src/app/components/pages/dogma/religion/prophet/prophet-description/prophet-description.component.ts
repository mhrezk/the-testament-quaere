import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Religion} from "../../../../../../interfaces/models/dogma/religion";
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {Prophet} from "../../../../../../interfaces/models/dogma/prophet";
import {ProphetService} from "../../../../../../services/models/dogma/prophet/prophet.service";

@Component({
  selector: 'app-prophet-description',
  templateUrl: './prophet-description.component.html',
  styleUrl: './prophet-description.component.css'
})
export class ProphetDescriptionComponent {
  @Input() prophet: Prophet;
  @Output() isDescription = new EventEmitter<boolean>();

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  constructor(private prophetService: ProphetService) {}

  isDescriptionEdited(editable: boolean) {
    this.isDescription.emit(editable);
  }

  updateDescription() {
    this.prophetService.updateProphet$(this.prophet.id, this.prophet).subscribe();
    this.isDescription.emit(false);
  }
}

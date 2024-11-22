import {Component, EventEmitter, Input, Output} from '@angular/core';
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {Deity} from "../../../../../../interfaces/models/dogma/mythology/deity";
import {DeityService} from "../../../../../../services/models/dogma/mythology/deity/deity.service";

@Component({
  selector: 'app-deity-description',
  templateUrl: './deity-description.component.html',
  styleUrl: './deity-description.component.css'
})
export class DeityDescriptionComponent {
  @Input() deity: Deity;
  @Output() isDescription = new EventEmitter<boolean>();

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  constructor(private deityService: DeityService) {}

  isDescriptionEdited(editable: boolean) {
    this.isDescription.emit(editable);
  }

  updateDescription() {
    this.deityService.updateDeity$(this.deity.id, this.deity).subscribe();
    this.isDescription.emit(false);
  }
}

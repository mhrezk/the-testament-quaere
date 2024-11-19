import {Component, EventEmitter, Input, Output} from '@angular/core';
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {NationDetails} from "../../../../../interfaces/models/places/nation-details";
import {NationDetailsService} from "../../../../../services/models/places/nation-details/nation-details.service";

@Component({
  selector: 'app-nation-details-history',
  templateUrl: './nation-details-history.component.html',
  styleUrl: './nation-details-history.component.css'
})
export class NationDetailsHistoryComponent {
  @Input() nationDetails: NationDetails;
  @Output() isHistory = new EventEmitter<boolean>();

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  constructor(private nationDetailsService: NationDetailsService) {}

  isHistoryEdited(editable: boolean) {
    this.isHistory.emit(editable);
  }

  updateHistory() {
    this.nationDetailsService.updateNationDetails(this.nationDetails.id, this.nationDetails).subscribe();
    this.isHistory.emit(false);
  }
}

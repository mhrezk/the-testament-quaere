import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NationDetails} from "../../../../../interfaces/models/places/nation-details";
import {NationDetailsService} from "../../../../../services/models/places/nation-details/nation-details.service";
import {Router} from "@angular/router";
import {NationStatus} from "../../../../../enums/nation-status";
import {NationType} from "../../../../../enums/nation-type";
import {GovernanceType} from "../../../../../enums/governance-type";

@Component({
  selector: 'app-nation-details-edit',
  templateUrl: './nation-details-edit.component.html',
  styleUrl: './nation-details-edit.component.css'
})
export class NationDetailsEditComponent implements OnInit {
  @Output() isEditing = new EventEmitter<boolean>();
  @Input() nationDetails: NationDetails;

  constructor(private nationDetailsService: NationDetailsService,
              private router: Router) {}

  ngOnInit() {
  }

  // Convert enum values into an array for iteration
  get nationTypeKeys() {
    return Object.keys(NationType).filter(
      (key) => key !== 'ALL' && isNaN(Number(key))
    );
  }

  get governanceTypeKeys() {
    return Object.keys(GovernanceType).filter(
      (key) => key !== 'ALL' && isNaN(Number(key))
    );
  }

  get nationStatusKeys() {
    return Object.keys(NationStatus).filter(
      (key) => key !== 'ALL' && isNaN(Number(key))
    );
  }

  onSubmit(nationDetails: NationDetails) {
    this.nationDetailsService.modifyNationDetails(this.nationDetails.id, nationDetails).subscribe(
      result => {
        this.onClose(false);
        this.router.navigateByUrl(`/nation-details/${this.nationDetails.id}/${nationDetails.nation}`);
      }
    );
    console.log(this.nationDetails);
  }

  onClose(editable: boolean) {
    this.isEditing.emit(editable);
  }
}

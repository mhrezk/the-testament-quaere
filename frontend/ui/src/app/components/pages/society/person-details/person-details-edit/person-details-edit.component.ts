import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PersonDetailsService} from "../../../../../services/models/society/person-details/person-details.service";
import {PersonDetails} from "../../../../../interfaces/models/society/person-details";
import {Router} from "@angular/router";
import {RaceService} from "../../../../../services/models/history/race/race.service";
import {Race} from "../../../../../interfaces/models/history/race";
//import {firstValueFrom} from "rxjs";
import {Lineage} from "../../../../../enums/lineage";
import {MaritalStatus} from "../../../../../enums/marital-status";
//import DOMPurify from 'dompurify';

@Component({
  selector: 'app-person-details-edit',
  templateUrl: './person-details-edit.component.html',
  styleUrl: './person-details-edit.component.css'
})
export class PersonDetailsEditComponent implements OnInit {
  protected readonly Object = Object;
  protected readonly Lineage = Lineage;
  protected readonly MaritalStatus = MaritalStatus;

  races: Race[];
  selectedRace: string;
  updatedPersonDetails: PersonDetails;
  @Output() isEditing = new EventEmitter<boolean>();
  //@Output() updatedPersonDetails = new EventEmitter<PersonDetails>();
  @Input() personDetails: PersonDetails;

  constructor(private personDetailsService: PersonDetailsService,
              private raceService: RaceService,
              private router: Router) {
  }

  ngOnInit() {
    this.getAllRaces();
  }

  modifyPersonDetails(personDetailsID: number, personDetails: PersonDetails) {
    this.personDetailsService.modifyPersonDetails(personDetailsID, personDetails).subscribe(
      (result) => {
        console.log(result);
        this.updatedPersonDetails = result.data.dataUpdated;
      }
    );
    console.log(this.updatedPersonDetails);
  }

  onSubmit(personDetails: PersonDetails) {
    this.personDetailsService.modifyPersonDetails(this.personDetails.id, personDetails).subscribe();
    console.log(this.personDetails);
    console.log(this.personDetails.biography);
    //this.updatedPersonDetails.emit(personDetailsForm.value);
    this.onClose(false);
    this.router.navigateByUrl(`/person-details/${this.personDetails.id}/${personDetails.firstName}/${personDetails.secondName}`);
  }

  onClose(editable: boolean) {
    this.isEditing.emit(editable);
  }

  onSelectRaceHandler(event: any) {
    this.selectedRace = event.target.value;
    console.log(this.selectedRace);
    // let temp = await firstValueFrom(this.raceService.getRaceByName$(name)); //uses async keyword for method
    // this.selectedRace = temp.data.datumRetrieved;
    this.personDetails.raceName = this.selectedRace;
    console.log(this.personDetails.raceName);
  }

  getAllRaces() {
    this.raceService.getRaces$.subscribe(
      result => this.races = result.data.dataRetrieved
    )
  }

  get lineageKeys() {
    return Object.keys(Lineage).filter(
      (key) => key !== 'ALL' && isNaN(Number(key))
    );
  }
}

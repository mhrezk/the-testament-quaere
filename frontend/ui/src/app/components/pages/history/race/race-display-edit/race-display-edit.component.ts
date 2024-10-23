import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {faCircleArrowLeft, faEdit, faTrash} from "@fortawesome/free-solid-svg-icons";
import {RaceService} from "../../../../../services/models/history/race/race.service";
import {ActivatedRoute, Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {Race} from "../../../../../interfaces/models/history/race";

@Component({
  selector: 'app-race-display-edit',
  templateUrl: './race-display-edit.component.html',
  styleUrl: './race-display-edit.component.css'
})
export class RaceDisplayEditComponent implements OnInit {
  @Input() retrievedRace: Race;
  @Output() isEditingRace = new EventEmitter<boolean>();

  raceID: string;
  raceName: string;

  race = new BehaviorSubject<CustomResponse>(null);
  selectedRace: Race;

  faCircleArrowLeft = faCircleArrowLeft;
  faEdit = faEdit;
  faTrash = faTrash;

  constructor(private raceService: RaceService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => this.raceID = result.get("id")
    );

    this.activatedRoute.paramMap.subscribe(
      result => this.raceName = result.get("name")
    );

    console.log(+this.raceID);
    console.log(this.raceName);

    await this.getRaceByID(+this.raceID);
  }

  async getRaceByID(raceID: number) {
    const result = await this.raceService.getRaceByID(raceID).toPromise();
    this.race.next(result.data.datumRetrieved);
    console.log(result);
    console.log(this.race);
    this.selectedRace = result.data.datumRetrieved;
  }

  deleteRace(raceID: number) {
    this.raceService.deleteRace(raceID).subscribe();
    this.router.navigateByUrl("/races");
  }

  closeEditing(doneEditing: boolean) {
    console.log(doneEditing);
    this.isEditingRace.emit(doneEditing);
    this.router.navigateByUrl(`/races/${this.raceID}/${this.raceName}`);
  }

  onSubmit(race: Race) {
    this.raceService.modifyRace(+this.raceID, race).subscribe();
    this.closeEditing(false);
    this.router.navigateByUrl(`/races/${this.raceID}/${race.name}`);
  }
}

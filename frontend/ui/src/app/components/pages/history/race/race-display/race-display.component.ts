import {Component, OnInit} from '@angular/core';
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {Router} from "@angular/router";
import {faEdit, faCircleArrowLeft, faTrash} from "@fortawesome/free-solid-svg-icons";
import {RaceService} from "../../../../../services/models/history/race/race.service";
import {BehaviorSubject} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {Race} from "../../../../../interfaces/models/history/race";

@Component({
  selector: 'app-race-display',
  templateUrl: './race-display.component.html',
  styleUrl: './race-display.component.css'
})
export class RaceDisplayComponent implements OnInit {
  isEditing: boolean = false;
  isDescription: boolean = false;

  raceID: number;
  raceName: string;

  race = new BehaviorSubject<CustomResponse>(null);

  selectedRace: Race;

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;
  faTrash = faTrash;

  constructor(private raceService: RaceService,
              private router: Router) {

  }

  async ngOnInit() {
    this.raceService.getRaceID$.subscribe(result => this.raceID = result);
    this.raceService.getRaceName$.subscribe(result => this.raceName = result);
    await this.getRaceByID(this.raceID);
  }

  async getRaceByID(raceID: number) {
    const result = await this.raceService.getRaceByID(raceID).toPromise();
    // this.raceService.getRaceByID(raceID).subscribe(result => {
    //   this.selectedRace = result.data.datumRetrieved;
    //   this.race.next(result);
    // });
    this.selectedRace = result.data.datumRetrieved;
    console.log(this.selectedRace);
  }

  deleteRace(raceID: number) {
    this.raceService.deleteRace$(raceID).subscribe();
    this.raceService.getAllRacesCount().subscribe();
    this.router.navigateByUrl("/races");
  }

  updateDescription() {
    this.raceService.updateRace(this.raceID, this.selectedRace).subscribe();
    this.isDescription = false;
  }

  routeToSubRaces(raceID: number, raceName: string) {
    this.router.navigateByUrl(`/sub-races/race/${raceID}/${raceName}`);
  }

  routeToRaces() {
    this.router.navigateByUrl(`/races`);
  }

  routeToRaceEdit(raceID: number, raceName: string) {
    this.router.navigateByUrl(`/races/${raceID}/${raceName}/edit`);
  }

  closeEditing(doneEditing: boolean) {
    this.isEditing = doneEditing;
  }
}

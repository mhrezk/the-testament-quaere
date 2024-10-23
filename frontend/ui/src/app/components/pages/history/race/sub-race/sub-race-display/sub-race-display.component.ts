import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {CustomResponse} from "../../../../../../interfaces/custom-response";
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {faEdit, faCircleArrowLeft, faTrash} from "@fortawesome/free-solid-svg-icons";
import {SubRace} from "../../../../../../interfaces/models/history/sub-race";
import {SubRaceService} from "../../../../../../services/models/history/sub-race/sub-race.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RaceService} from "../../../../../../services/models/history/race/race.service";

@Component({
  selector: 'app-sub-race-display',
  templateUrl: './sub-race-display.component.html',
  styleUrl: './sub-race-display.component.css'
})
export class SubRaceDisplayComponent implements OnInit {
  isEditing: boolean = false;
  isDescription: boolean = false;

  subRaceID: string;
  subRaceName: string;

  raceID: number;

  subRace = new BehaviorSubject<CustomResponse>(null);

  selectedSubRace: SubRace;

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;
  faTrash = faTrash;

  constructor(private subRaceService: SubRaceService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private raceService: RaceService) {}

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => this.subRaceID = result.get("id")
    );
    this.activatedRoute.paramMap.subscribe(
      result => this.subRaceName = result.get("name")
    );
    this.raceService.getRaceID$.subscribe(
      result => this.raceID = result
    );
    await this.getSubRaceByID(+this.subRaceID);
  }

  async getSubRaceByID(subRaceID: number) {
    const result = await this.subRaceService.getSubRaceByID(subRaceID).toPromise();
    // this.subRaceService.getSubRaceByID(subRaceID).subscribe(result => {
    //   this.selectedSubRace = result.data.datumRetrieved;
    //   this.subRace.next(result);
    // });
    this.selectedSubRace = result.data.datumRetrieved;
    this.subRace.next(result);
  }

  deleteSubRace(subRaceID: number, raceID: number, raceName: string) {
    this.subRaceService.deleteSubRace(subRaceID).subscribe();
    this.router.navigateByUrl(`/sub-races/race/${raceID}/${raceName}`);
  }

  updateDescription() {
    this.subRaceService.updateSubRace(+this.subRaceID, this.subRaceName, this.selectedSubRace).subscribe();
    this.isDescription = false;
  }

  routeToSubRaces(raceID: number, raceName: string) {
    this.router.navigateByUrl(`/sub-races/race/${raceID}/${raceName}`);
  }
}

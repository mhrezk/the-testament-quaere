import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {AppState} from "../../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../../interfaces/custom-response";
import {DataState} from "../../../../../../enums/data-state";
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {Prophet} from "../../../../../../interfaces/models/dogma/prophet";
import {ActivatedRoute, Router} from "@angular/router";
import {ProphetService} from "../../../../../../services/models/dogma/prophet/prophet.service";

@Component({
  selector: 'app-prophet-display',
  templateUrl: './prophet-display.component.html',
  styleUrl: './prophet-display.component.css'
})
export class ProphetDisplayComponent implements OnInit {
  isEditing: boolean = false;
  isDescription: boolean = false;

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  selectedProphet: Prophet;
  religionID: string;
  religionName: string;
  prophetID: string;
  prophetName: string;

  constructor(private prophetService: ProphetService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {}

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.religionID = result.get("id");
        this.religionName = result.get("name");
        this.prophetID = result.get("prophetID");
        this.prophetName = result.get("prophetName");
      }
    )
    await this.getProphetByID(+this.prophetID);
  }

  async getProphetByID(prophetID: number) {
    const result = await this.prophetService.getProphetByID(prophetID).toPromise();
    this.selectedProphet = result.data.datumRetrieved;
    console.log(this.selectedProphet);
  }

  deleteProphet(prophetID: number) {
    this.prophetService.deleteProphet$(prophetID).subscribe((result) => {
      console.log(result);
      this.routeToProphets();
    });
  }

  closeEditing(editable: boolean) {
    this.isEditing = editable;
  }

  closeDescriptionEditing(editable: boolean) {
    this.isDescription = editable;
  }

  routeToProphets() {
    this.router.navigateByUrl(`/religions/${+this.religionID}/${this.religionName}/prophets`);
  }
}

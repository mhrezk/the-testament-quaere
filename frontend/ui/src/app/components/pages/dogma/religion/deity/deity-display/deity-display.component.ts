import {Component, OnInit} from '@angular/core';
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {Observable} from "rxjs";
import {AppState} from "../../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../../interfaces/custom-response";
import {DataState} from "../../../../../../enums/data-state";
import {Deity} from "../../../../../../interfaces/models/dogma/mythology/deity";
import {ActivatedRoute, Router} from "@angular/router";
import {DeityService} from "../../../../../../services/models/dogma/mythology/deity/deity.service";

@Component({
  selector: 'app-deity-display',
  templateUrl: './deity-display.component.html',
  styleUrl: './deity-display.component.css'
})
export class DeityDisplayComponent implements OnInit {
  isEditing: boolean = false;
  isDescription: boolean = false;

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  selectedDeity: Deity;
  religionID: string;
  religionName: string;
  deityID: string;
  deityName: string;

  constructor(private deityService: DeityService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.religionID = result.get("id");
        this.religionName = result.get("name");
        this.deityID = result.get("deityID");
        this.deityName = result.get("deityName");
      }
    )
    await this.getDeityByID(+this.deityID);
  }

  async getDeityByID(deityID: number) {
    const result = await this.deityService.getDeityByID(deityID).toPromise();
    this.selectedDeity = result.data.datumRetrieved;
    console.log(this.selectedDeity);
  }

  deleteDeity(deityID: number) {
    this.deityService.deleteDeity$(deityID).subscribe((result) => {
      console.log(result);
      this.routeToDeities();
    });
  }

  closeEditing(editable: boolean) {
    this.isEditing = editable;
  }

  closeDescriptionEditing(editable: boolean) {
    this.isDescription = editable;
  }

  routeToDeities() {
    this.router.navigateByUrl(`/religions/${+this.religionID}/${this.religionName}/deities`);
  }
}

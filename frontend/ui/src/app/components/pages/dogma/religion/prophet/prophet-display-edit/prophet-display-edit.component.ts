import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Prophet} from "../../../../../../interfaces/models/dogma/prophet";
import {map, Observable, of} from "rxjs";
import {AppState} from "../../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../../interfaces/custom-response";
import {DataState} from "../../../../../../enums/data-state";
import {ActivatedRoute, Router} from "@angular/router";
import {ProphetService} from "../../../../../../services/models/dogma/prophet/prophet.service";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-prophet-display-edit',
  templateUrl: './prophet-display-edit.component.html',
  styleUrl: './prophet-display-edit.component.css'
})

export class ProphetDisplayEditComponent implements OnInit {
  @Output() isEditing = new EventEmitter<boolean>();
  @Input() prophet: Prophet;

  selectedProphet: Prophet;

  prophetID: string;
  religionID: string;
  religionName: string;

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;

  constructor(private prophetService: ProphetService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {}

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.prophetID = result.get("prophetID");
        this.religionID = result.get("id");
        this.religionName = result.get("name");
      }
    )
    await this.getProphetByID(+this.prophetID);
  }

  async getProphetByID(prophetID: number) {
    const result = await this.prophetService.getProphetByID(prophetID).toPromise();
    this.selectedProphet = result.data.datumRetrieved;
    console.log(this.selectedProphet);
  }

  onSubmit(prophet: Prophet) {
    console.log(prophet);
    // Send the request to update the religion details
    this.prophetService.updateProphet$(this.prophet.id, prophet)
      .pipe(
        map((response) => {
          console.log('Prophet modified:', response);
          // Update the religion object locally after modification
          // Assuming religion has an ID and fields you want to update
          const updatedProphet = response.data.dataUpdated;
          Object.assign(prophet, updatedProphet);  // Merge the updated details
          this.onClose(false);
          this.router.navigateByUrl(`/religions/${+this.religionID}/${this.religionName}/prophets/${this.prophet.id}/${updatedProphet.name}`);
        }),
        catchError((caughtError: string) => {
          console.error('Error modifying prophet:', caughtError);
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      )
      .subscribe(
        (state) => {
          // Handle the new state if you are using a store, subject, or state management
          console.log('State after prophet modification:', state);
        }
      );
  }

  onClose(editable: boolean) {
    this.isEditing.emit(editable);
  }
}

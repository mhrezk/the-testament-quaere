import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {catchError} from "rxjs/operators";
import {AppState} from "../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {DataState} from "../../../../../enums/data-state";
import {Religion} from "../../../../../interfaces/models/dogma/religion";
import {ReligionService} from "../../../../../services/models/dogma/religion/religion.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-religion-display-edit',
  templateUrl: './religion-display-edit.component.html',
  styleUrl: './religion-display-edit.component.css'
})
export class ReligionDisplayEditComponent implements OnInit {
  @Output() isEditing = new EventEmitter<boolean>();
  @Input() religion: Religion;

  religionID: string;
  selectedReligion: Religion;

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;

  constructor(private religionService: ReligionService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {}

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => this.religionID = result.get("id")
    )
    await this.getReligionByID(+this.religionID);
  }

  async getReligionByID(religionID: number) {
    const result = await this.religionService.getReligionByID(religionID).toPromise();
    this.selectedReligion = result.data.datumRetrieved;
    console.log(this.selectedReligion);
  }

  onSubmit(religion: Religion) {
    console.log(religion);
    // Send the request to update the religion details
    this.religionService.updateReligion$(this.religion.id, religion)
      .pipe(
        map((response) => {
          console.log('Religion modified:', response);
          // Update the religion object locally after modification
          // Assuming religion has an ID and fields you want to update
          const updatedReligion = response.data.dataUpdated;
          Object.assign(religion, updatedReligion);  // Merge the updated details
          this.onClose(false);
          this.router.navigateByUrl(`/religions/${this.religion.id}/${updatedReligion.name}`);
          // Optionally, you can manage application state here if needed
          // return {
          //   dataState: DataState.LOADED,
          //   appData: {
          //     ...response,  // Or any other data you need to return
          //     dataUpdated: religion,
          //   },
          // };
        }),
        // startWith({
        //   dataState: DataState.LOADED,
        //   appData: religion, // Initial data state before modification
        // }),
        catchError((caughtError: string) => {
          console.error('Error modifying religion:', caughtError);
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      )
      .subscribe(
        (state) => {
          // Handle the new state if you are using a store, subject, or state management
          console.log('State after religion modification:', state);
        }
      );
  }

  onClose(editable: boolean) {
    this.isEditing.emit(editable);
  }
}

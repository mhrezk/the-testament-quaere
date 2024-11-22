import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Prophet} from "../../../../../../interfaces/models/dogma/prophet";
import {map, Observable, of} from "rxjs";
import {AppState} from "../../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../../interfaces/custom-response";
import {DataState} from "../../../../../../enums/data-state";
import {Deity} from "../../../../../../interfaces/models/dogma/mythology/deity";
import {DeityService} from "../../../../../../services/models/dogma/mythology/deity/deity.service";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-deity-display-edit',
  templateUrl: './deity-display-edit.component.html',
  styleUrl: './deity-display-edit.component.css'
})
export class DeityDisplayEditComponent implements OnInit {
  @Output() isEditing = new EventEmitter<boolean>();
  @Input() deity: Deity;

  selectedDeity: Deity;

  deityID: string;
  religionID: string;
  religionName: string;

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;

  constructor(private deityService: DeityService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.deityID = result.get("deityID");
        this.religionID = result.get("id");
        this.religionName = result.get("name");
      }
    )
    await this.getDeityByID(+this.deityID);
  }

  async getDeityByID(deityID: number) {
    const result = await this.deityService.getDeityByID(deityID).toPromise();
    this.selectedDeity = result.data.datumRetrieved;
    console.log(this.selectedDeity);
  }

  onSubmit(deity: Deity) {
    console.log(deity);
    // Send the request to update the religion details
    this.deityService.updateDeity$(this.deity.id, deity)
      .pipe(
        map((response) => {
          console.log('Deity modified:', response);
          // Update the religion object locally after modification
          // Assuming religion has an ID and fields you want to update
          const updatedDeity = response.data.dataUpdated;
          Object.assign(deity, updatedDeity);  // Merge the updated details
          this.onClose(false);
          this.router.navigateByUrl(`/religions/${+this.religionID}/${this.religionName}/deities/${this.deity.id}/${updatedDeity.name}`);
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
          console.log('State after religion modification:', state);
        }
      );
  }

  onClose(editable: boolean) {
    this.isEditing.emit(editable);
  }
}

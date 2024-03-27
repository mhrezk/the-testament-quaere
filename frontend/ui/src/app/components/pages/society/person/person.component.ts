import {Component} from '@angular/core';
import {PersonService} from "../../../../services/society/person/person.service";
import {Gender} from "../../../../enums/gender/gender";
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {AppState} from "../../../../interfaces/application-state/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response/custom-response";
import {DataState} from "../../../../enums/data-state/data-state";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrl: './person.component.css'
})
export class PersonComponent {
  appState$: Observable<AppState<CustomResponse>>;
  // protected window: any = window;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);

  constructor(private personService: PersonService) {
  }

  filterByGender(gender: Gender) {
    this.appState$ = this.personService.filterByGender$(gender, this.dataSubject.value)
      .pipe(
        map((result) => {
          this.dataSubject.next(result); //stores result in dataSubject to be used in another method or later
          return {
            dataState: DataState.LOADED,
            appData: result,
          };
        }),
        startWith({
          dataState: DataState.LOADED,
          appData: this.dataSubject.value
        }),
        catchError((caughtError: string) => {
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
  }
}

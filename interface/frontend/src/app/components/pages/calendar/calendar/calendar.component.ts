import {Component, OnInit} from '@angular/core';
import {DayService} from "../../../../services/calendar/day/day-service.service";
import {map, Observable, of, startWith} from "rxjs";
import {AppState} from "../../../../interfaces/application-state/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response/custom-response";
import {DataState} from "../../../../enums/data-state/data-state";
import {catchError} from "rxjs/operators";
import {Day} from "../../../../interfaces/models/calendar/day/day";

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent implements OnInit {
  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  headers = [
    {
      key: "name",
      value: "Day Name"
    },
    {
      key: "number",
      value: "Day Number"
    },
    {
      key: "description",
      value: "Day Description"
    }
  ]

  constructor(private dayService: DayService) {}

  ngOnInit(): void {
    this.appState$ = this.dayService.getDays$
      .pipe(
        map((result) => {
          return {
            dataState: DataState.LOADED,
            data: result
          }
        }),
        startWith({
          dataState: DataState.LOADING,
          // data: null
        }),
        catchError((caughtError: string) => {
          return of({
            dataState: DataState.ERROR,
            error: caughtError
          })
        })
      );
  }
}

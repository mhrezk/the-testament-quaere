import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {DataState} from "../../../enums/data-state/data-state";
import {catchError} from "rxjs/operators";
import {DayService} from "../../../services/calendar/day/day.service";
import {CustomResponse} from "../../../interfaces/custom-response/custom-response";
import {AppState} from "../../../interfaces/application-state/app-state";

@Component({
  selector: 'app-day-dialog',
  templateUrl: './day-dialog.component.html',
  styleUrl: './day-dialog.component.css'
})
export class DayDialogComponent {
  appState$: Observable<AppState<CustomResponse>>;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();
  constructor(private dayService: DayService) {
  }

  saveDay(dayForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.dayService
      .saveDay$(dayForm.value) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next({
            ...result,
            data: {
              dataRetrieved: [
                result.data.dataSaved,
                ...this.dataSubject.value.data.dataRetrieved,
              ],
            },
          });
          document.getElementById("closeModal").click() //close modal
          this.isLoading.next(false);
          dayForm.resetForm(); //resets form
          return {
            dataState: DataState.LOADED,
            data: this.dataSubject.value,
          };
        }),
        startWith({
          dataState: DataState.LOADED,
          data: this.dataSubject.value, //begin with pre-loaded data
        }),
        catchError((caughtError: string) => {
          this.isLoading.next(false);
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
  }

  closeDialog() {

  }

}

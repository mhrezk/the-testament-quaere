import { Component, OnInit } from '@angular/core';
import { DayService } from '../../../services/calendar/day/day.service';
import { BehaviorSubject, map, Observable, of, startWith } from 'rxjs';
import { AppState } from '../../../interfaces/application-state/app-state';
import { CustomResponse } from '../../../interfaces/custom-response/custom-response';
import { DataState } from '../../../enums/data-state/data-state';
import { catchError } from 'rxjs/operators';
import {
  faTrash,
  faEdit,
} from '@fortawesome/free-solid-svg-icons';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css',
})
export class CalendarComponent implements OnInit {
  appState$: Observable<AppState<CustomResponse>>;
  // protected window: any = window;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();
  private isClicked = new BehaviorSubject<boolean>(false);
  isClicked$ = this.isClicked.asObservable();
  faTrash = faTrash;
  faEdit = faEdit;
  headers = [
    {
      key: 'name',
      value: 'Day Name',
    },
    // {
    //   key: 'number',
    //   value: 'Day Number',
    // },
    {
      key: 'description',
      value: 'Day Description',
    },
  ];

  constructor(private dayService: DayService) {}

  ngOnInit(): void {
    this.appState$ = this.dayService.getDays$.pipe(
      map((result) => {
        this.dataSubject.next(result); //stores result in dataSubject to be used in another method or later
        return {
          dataState: DataState.LOADED,
          data: result,
        };
      }),
      startWith({
        dataState: DataState.LOADING,
        // data: null
      }),
      catchError((caughtError: string) => {
        return of({
          dataState: DataState.ERROR,
          error: caughtError,
        });
      })
    );
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

  activateModal() {
    const modal = document.getElementById("addDayModal");
    if(modal != null) {
      modal.style.display = "block";
    }
  }

  // openDayModal() {
  //   window.location.href = 'addDayModal';
  // }
}

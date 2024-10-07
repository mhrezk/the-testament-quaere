import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {MatDialog} from "@angular/material/dialog";

import {
  faTrash,
  faEdit,
} from '@fortawesome/free-solid-svg-icons';

import {BehaviorSubject, map, Observable, of, startWith} from 'rxjs';
import {catchError} from 'rxjs/operators';

import {DayService} from '../../../services/models/calendar/day/day.service';
import {AppState} from '../../../interfaces/app-state';
import {CustomResponse} from '../../../interfaces/custom-response';
import {DataState} from '../../../enums/data-state';
import {Day} from "../../../interfaces/models/calendar/day";

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css',
})
export class CalendarComponent implements OnInit {
  selectedDay: Day;
  appState$: Observable<AppState<CustomResponse>>;
  // protected window: any = window;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();
  isTableShown: boolean = false;
  isUpdated: boolean = false;
  isClicked: boolean = false;
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

  constructor(private dayService: DayService, private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.isTableShown = true;
    this.getAllDays();
  }

  getAllDays() {
    this.appState$ = this.dayService.getDays$
      .pipe(
        map((result) => {
          this.dataSubject.next(result); //stores result in dataSubject to be used in another method or later
          return {
            dataState: DataState.LOADED,
            appData: result,
          };
        }),
        startWith({
          dataState: DataState.LOADING,
          // appData: null
        }),
        catchError((caughtError: string) => {
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
  }

  getDayByID(dayID: number) {
    this.dayService.getDayByID(dayID).subscribe(result => {
      this.selectedDay = result.data.datumRetrieved;
    })
  }

  saveDay(dayForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.dayService
      .saveDay$(dayForm.value) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: [
                  result.data.dataSaved,
                  ...this.dataSubject.value.data.dataRetrieved,
                ],
              },
            });
          //document.getElementById("closeModal").click() //close modal
          this.isTableShown = true;
          this.isClicked = false;
          this.isLoading.next(false);
          dayForm.resetForm(); //resets form
          return {
            dataState: DataState.LOADED,
            appData: this.dataSubject.value,
          };
        }),
        startWith({
          dataState: DataState.LOADED,
          appData: this.dataSubject.value, //begin with pre-loaded data
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

  deleteDay(day: Day) {
    this.appState$ = this.dayService
      .deleteDay$(day.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((d) =>
                  d.id !== day.id //delete the record that matches d.id === day.id
                )
              }
            }
          );
          return {
            dataState: DataState.LOADED,
            appData: this.dataSubject.value,
          };
        }),
        startWith({
          dataState: DataState.LOADED,
          appData: this.dataSubject.value, //begin with pre-loaded data
        }),
        catchError((caughtError: string) => {
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
  }

  modifyDay(day: Day) {
    this.isLoading.next(true);
    this.appState$ = this.dayService.modifyDay$(day.id, day).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(day => day.id === result.data.dataUpdated.id); //loops through the array and finds the record whose id matches the updated day from the backend
        this.dataSubject.value.data.dataRetrieved[index] = result.data.dataUpdated; //replaces old day with updated day
        this.isTableShown = true;
        this.isUpdated = false;
        this.isLoading.next(false);
        return {
          dataState: DataState.LOADED,
          appData: this.dataSubject.value
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

  updateDay(dayForm: NgForm) {
    this.dayService.modifyDay(dayForm.value).subscribe(result => {
      for (let i = 0; i < this.dataSubject.value.data.dataRetrieved.length; i++) {
        if (this.selectedDay.id === this.dataSubject.value.data.dataRetrieved[i].id) {
          this.dataSubject.value.data.dataRetrieved.splice(i, 1, this.selectedDay);
        }
      }
    });
    this.isUpdated = false;
  }

  // openDialog() {
  //   this.dialog.open(DayDialogComponent, {
  //     width: "400px"
  //   });
  // }

  // activateModal() {
  //   const modal = document.getElementById("addDayModal");
  //   if(modal != null) {
  //     modal.style.display = "block";
  //   }
  // }

  // openDayModal() {
  //   window.location.href = 'addDayModal';
  // }
}

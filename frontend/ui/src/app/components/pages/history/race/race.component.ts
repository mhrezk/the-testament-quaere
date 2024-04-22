import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";

import {
  faTrash,
  faEdit,
} from '@fortawesome/free-solid-svg-icons';

import {AppState} from "../../../../interfaces/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {DataState} from "../../../../enums/data-state";
import { RaceService } from '../../../../services/models/history/race/race.service';
import { Race } from '../../../../interfaces/models/history/race';

@Component({
  selector: 'app-race',
  templateUrl: './race.component.html',
  styleUrl: './race.component.css'
})
export class RaceComponent implements OnInit {
  selectedRace: Race;
  appState$: Observable<AppState<CustomResponse>>;
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
      value: 'Race',
    },
    // {
    //   key: 'description',
    //   value: 'Description'
    // }
  ];


  constructor(private raceService: RaceService) {}

  ngOnInit() {
    this.getAllRaces();
  }

  getAllRaces() {
    this.isTableShown = true;
    this.appState$ = this.raceService.getRaces$
      .pipe(
        map((result) => {
          this.dataSubject.next(result);
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

  getRaceByID(raceID: number) {
    this.raceService.getRaceByID(raceID).subscribe(result => {
      this.selectedRace = result.data.datumRetrieved;
    })
  }

  saveRace(raceForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.raceService
      .saveRace$(raceForm.value) //or dayForm.value as Day or <Day> dayForm.value
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
          this.isClicked = false;
          this.isTableShown = true;
          this.isLoading.next(false);
          raceForm.resetForm();
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

  deleteRace(race: Race) {
    this.appState$ = this.raceService
      .deleteRace$(race.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((r) =>
                  r.id !== race.id //delete the record that matches d.id === day.id
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

  modifyRace(race: Race) {
    this.isLoading.next(true);
    this.appState$ = this.raceService.modifyRace$(race).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(race => race.id === result.data.dataUpdated.id); //loops through the array and finds the record whose id matches the updated day from the backend
        this.dataSubject.value.data.dataRetrieved[index] = result.data.dataUpdated; //replaces old day with updated day
        this.isUpdated = false;
        this.isTableShown = true;
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
}

import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, firstValueFrom, map, Observable, of, startWith} from "rxjs";
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";

import {
  faTrash,
  faEdit,
} from '@fortawesome/free-solid-svg-icons';

import {AppState} from "../../../../interfaces/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {DataState} from "../../../../enums/data-state";
import {PersonService} from "../../../../services/society/person/person.service";
import {Gender} from "../../../../enums/gender";
import {Person} from "../../../../interfaces/models/society/person";
import {Race} from "../../../../interfaces/models/history/race";
import {RaceService} from "../../../../services/history/race/race.service";

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrl: './person.component.css'
})
export class PersonComponent implements OnInit {
  currentPage:number  = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  races: Race[];
  selectedRace: Race;
  selectedPerson: Person;
  appState$: Observable<AppState<CustomResponse>>;
  readonly gender = Gender;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();
  isUpdated: boolean = false;
  isClicked: boolean = false;
  faTrash = faTrash;
  faEdit = faEdit;
  headers = [
    {
      key: 'name',
      value: 'Person Name',
    },
    {
      key: 'race',
      value: 'Race',
    },
    {
      key: 'gender',
      value: 'Gender',
    }
  ];

  constructor(private personService: PersonService,
              private raceService: RaceService) {
  }

  ngOnInit(): void {
    this.getPaginatedPeople(this.currentPage, this.tableSize);
    this.getAllRaces();
  }

  getAllRaces() {
    this.raceService.getRaces$.subscribe(
      result => this.races = result.data.dataRetrieved
    )
  }

  getPaginatedPeople(pageNumber: number, pageSize: number) {
    this.appState$ = this.personService.getPaginatedPeople$(pageNumber, pageSize)
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

  getPersonByID(personID: number) {
    this.personService.getPersonByID(personID).subscribe(result => {
      this.selectedPerson = result.data.datumRetrieved;
    })
  }

  savePerson(personForm: NgForm) {
    this.selectedPerson = personForm.value;
    this.selectedPerson.race = this.selectedRace;
    this.isLoading.next(true);
    this.appState$ = this.personService
      .savePerson$(this.selectedPerson) //or dayForm.value as Day or <Day> dayForm.value
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
          this.isLoading.next(false);
          personForm.resetForm({gender: this.gender.MALE}); //resets form with a default value
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

  deletePerson(person: Person) {
    this.appState$ = this.personService
      .deletePerson$(person.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((p) =>
                  p.id !== person.id //delete the record that matches d.id === day.id
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

  modifyPerson(person: Person) {
    this.isLoading.next(true);
    this.appState$ = this.personService.modifyPerson$(person).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(person => person.id === result.data.dataUpdated.id); //loops through the array and finds the record whose id matches the updated day from the backend
        this.dataSubject.value.data.dataRetrieved[index] = result.data.dataUpdated; //replaces old day with updated day
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

  filterPeopleByGender(gender: Gender) {
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

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedPeople(this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedPeople(this.currentPage, this.tableSize);
  }

  async onSelectRaceHandler(event: any) {
    let name = event.target.value;
    let temp = await firstValueFrom(this.raceService.getRaceByName$(name));
    this.selectedRace = temp.data.datumRetrieved;
  }
}

import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
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
import {Router} from "@angular/router";

@Component({
  selector: 'app-race',
  templateUrl: './race.component.html',
  styleUrl: './race.component.css'
})
export class RaceComponent implements OnInit {
  currentPage: number  = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  countSubject = new BehaviorSubject<number>(0)
  count$ = this.countSubject.asObservable();

  selectedRace: Race;
  races: Race[] = [];
  checkedRaces: Race[] = [];

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  isTableShown: boolean = false;
  isUpdated: boolean = false;
  isClicked: boolean = false;
  isMasterSelected: boolean = false;
  showError: boolean = false;

  enteredName: string;

  doesExist = new BehaviorSubject<CustomResponse>(null);

  errorMessage: string = "already exists in the database! Duplicate entries are disallowed!";

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


  constructor(private raceService: RaceService,
              private router: Router,
              /*private changeDetection: ChangeDetectorRef*/) {}

  ngOnInit() {
    this.getPaginatedRaces(this.currentPage, this.tableSize);
    this.getAllRacesTotal();
  }

  getAllRacesTotal() {
    this.raceService.getAllRacesCount().subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    )
    console.log(this.countSubject.value);
  }

  getPaginatedRaces(pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.raceService.getPaginatedRaces$(pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.races = result.data.dataRetrieved;
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

  async doesRaceNameExist(raceName: string) {
    const result = await this.raceService.doesRaceNameExist(raceName).toPromise();
    // this.raceService.doesRaceNameExist(raceName).subscribe(result => {
    //   this.doesExist.next(result.data.datumRetrieved);
    //   //this.changeDetection.detectChanges();
    // });
    this.doesExist.next(result);
    console.log(this.doesExist.value.data.datumRetrieved);
    return this.doesExist.value.data.datumRetrieved;
  }

  async saveRace(raceForm: NgForm) {
    if(await this.doesRaceNameExist(raceForm.value.name)) {
      this.enteredName = raceForm.value.name;
      this.showError = true;
      this.isClicked = false;
    } else {
      this.isLoading.next(true);
      this.appState$ = this.raceService
        .saveRace$(raceForm.value) //or dayForm.value as Day or <Day> dayForm.value
        .pipe(
          map((result) => {
            this.dataSubject.next({ //this lists everything in ascending insertion order
              ...result,
              data: {
                dataRetrieved: [
                  ...this.dataSubject.value.data.dataRetrieved, // Keep the existing entries
                  result.data.dataSaved, // Add the new entry at the end
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
      this.getAllRacesTotal();
    }
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
                  r.id !== race.id //delete the record that matches r.id === race.id
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
    this.getAllRacesTotal();
    if(race.name.toLowerCase() === "none") {
      window.location.reload();
    }
  }

  modifyRace(race: Race) {
    this.isLoading.next(true);
    this.appState$ = this.raceService.modifyRace$(race.id, race).pipe(
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

  deleteRaces(races: Race[]) {
    for(let race of races) {
      this.raceService.deleteRace(race.id).subscribe();
    }
    this.getAllRacesTotal();
  }

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedRaces(this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedRaces(this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for(let race of this.races) {
      race.isSelected = this.isMasterSelected;
    }
    this.getCheckedRaces();
  }

  isAllSelected() {
    this.isMasterSelected = this.races.every(race => race.isSelected);
    this.getCheckedRaces();
  }

  getCheckedRaces() {
    this.checkedRaces = [];
    for(let checkedRace of this.races) {
      if(checkedRace.isSelected) {
        this.checkedRaces.push(checkedRace);
      }
    }
  }

  hasSelected() {
    return this.races.some(race => race.isSelected);
  }

  // validateRaceName(event: Event) {
  //   const input = (event.target as HTMLInputElement).value;
  //   this.doesRaceNameExist(input.toUpperCase());
  //   console.log(this.doesExist.value);
  //   console.log(this.errorMessage);
  //   if(this.doesExist.value) {
  //     this.errorMessage = "Name already exists in the database!"
  //   } else {
  //     this.errorMessage = null;
  //   }
  // }

  routeToDisplay(raceID: number, raceName: string) {
    this.raceService.setRaceDetails(raceID, raceName);
    this.router.navigateByUrl(`races/${raceID}/${raceName}`);
  }
}

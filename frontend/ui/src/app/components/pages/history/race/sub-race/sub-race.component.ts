import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {AppState} from "../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {DataState} from "../../../../../enums/data-state";
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {SubRace} from "../../../../../interfaces/models/history/sub-race";
import {ActivatedRoute, Router} from "@angular/router";
import {SubRaceService} from "../../../../../services/models/history/sub-race/sub-race.service";
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-sub-race',
  templateUrl: './sub-race.component.html',
  styleUrl: './sub-race.component.css'
})
export class SubRaceComponent implements OnInit {
  currentPage: number  = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  selectedSubRace: SubRace;
  subRaces: SubRace[] = [];
  checkedSubRaces: SubRace[] = [];

  countSubject = new BehaviorSubject<number>(0)
  count$ = this.countSubject.asObservable();

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
  raceName: string;
  raceID: string;

  doesExist = new BehaviorSubject<CustomResponse>(null);

  errorMessage: string = "already exists in the database! Duplicate entries are disallowed!";

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  headers = [
    {
      key: 'name',
      value: 'Sub-race',
    }
  ];

  constructor(private subRaceService: SubRaceService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.raceName = params.get("name");
    });
    this.activatedRoute.paramMap.subscribe(params => {
      this.raceID = params.get("id");
    });
    this.getPaginatedSubRacesByRaceName(this.raceName, this.currentPage, this.tableSize);
    this.getAllSubRacesTotal();
  }

  getAllSubRacesTotal() {
    this.subRaceService.getAllSubRacesCount().subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    )
  }

  getPaginatedSubRaces(pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.subRaceService.getPaginatedSubRaces$(pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.subRaces = result.data.dataRetrieved;
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

  getPaginatedSubRacesByRaceName(raceName: string, pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.subRaceService.getPaginatedSubRacesByRaceName$(raceName, pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.subRaces = result.data.dataRetrieved;
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

  async getSubRaceByID(subRaceID: number) {
    const result = await this.subRaceService.getSubRaceByID(subRaceID).toPromise();
    this.selectedSubRace = result.data.datumRetrieved;
    // this.subRaceService.getSubRaceByID(subRaceID).subscribe(result => {
    //   this.selectedSubRace = result.data.datumRetrieved;
    // })
  }

  async doesSubRaceNameExist(raceName: string) {
    const result = await this.subRaceService.doesSubRaceNameExist(raceName).toPromise();
    this.doesExist.next(result);
    console.log(this.doesExist.value.data.datumRetrieved);
    return this.doesExist.value.data.datumRetrieved;
  }

  async saveSubRace(subRaceForm: NgForm) {
    if(await this.doesSubRaceNameExist(subRaceForm.value.name)) {
      this.enteredName = subRaceForm.value.name;
      this.showError = true;
      this.isClicked = false;
    } else {
      this.isLoading.next(true);
      this.appState$ = this.subRaceService
        .saveSubRace$(subRaceForm.value, this.raceName) //or dayForm.value as Day or <Day> dayForm.value
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
            subRaceForm.resetForm();
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
      this.getAllSubRacesTotal();
    }
  }

  deleteSubRace(subRace: SubRace) {
    this.appState$ = this.subRaceService
      .deleteSubRace$(subRace.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((s) =>
                  s.id !== subRace.id //delete the record that matches r.id === race.id
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
    this.getAllSubRacesTotal();
  }

  modifySubRace(subRace: SubRace) {
    this.isLoading.next(true);
    this.appState$ = this.subRaceService.modifySubRace$(subRace.id, this.raceName, subRace).pipe(
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

  deleteSubRaces(subRaces: SubRace[]) {
    for(let subRace of subRaces) {
      this.subRaceService.deleteSubRace(subRace.id).subscribe();
    }
    this.getAllSubRacesTotal();
  }

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedSubRaces(this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedSubRaces(this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for(let subRace of this.subRaces) {
      subRace.isSelected = this.isMasterSelected;
    }
    this.getCheckedSubRaces();
  }

  isAllSelected() {
    this.isMasterSelected = this.subRaces.every(subRace => subRace.isSelected);
    this.getCheckedSubRaces();
  }

  getCheckedSubRaces() {
    this.checkedSubRaces = [];
    for(let checkedRace of this.subRaces) {
      if(checkedRace.isSelected) {
        this.checkedSubRaces.push(checkedRace);
      }
    }
  }

  hasSelected() {
    return this.subRaces.some(subRace => subRace.isSelected);
  }

  routeToDisplay(subRaceID: number, subRaceName: string) {
    this.router.navigateByUrl(`/sub-races/${subRaceID}/${subRaceName}`)
  }

  routeToRaceDisplay(raceID: number, raceName: string) {
    this.router.navigateByUrl(`/races/${raceID}/${raceName}`)
  }
}

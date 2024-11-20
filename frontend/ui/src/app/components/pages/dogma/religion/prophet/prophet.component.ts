import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {AppState} from "../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {DataState} from "../../../../../enums/data-state";
import {
  faTrash,
  faEdit,
  faPlusCircle, faCircleArrowLeft
} from '@fortawesome/free-solid-svg-icons';
import {Prophet} from "../../../../../interfaces/models/dogma/prophet";
import {ProphetService} from "../../../../../services/models/dogma/prophet/prophet.service";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError} from "rxjs/operators";
import {NgForm, Validators} from "@angular/forms";

@Component({
  selector: 'app-prophet',
  templateUrl: './prophet.component.html',
  styleUrl: './prophet.component.css'
})
export class ProphetComponent implements OnInit {
  currentPage:number  = 1;
  tableSize: number = 5;
  tableSizes: number[] = [5, 10, 20];

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  countSubject = new BehaviorSubject<number>(0);
  count$ = this.countSubject.asObservable();

  selectedProphet: Prophet;
  prophets: Prophet[];
  checkedProphets: Prophet[];

  religionID: string;
  religionName: string;

  isUpdated: boolean = false;
  isClicked: boolean = false;
  isTableShown: boolean = false;
  isMasterSelected: boolean = false;

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;
  faPlusCircle = faPlusCircle;
  headers = [
    {
      key: 'name',
      value: 'Name',
    }
  ];

  constructor(private prophetService: ProphetService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {

  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.religionName = result.get("name");
        this.religionID = result.get("id");
      }
    );
    this.getPaginatedProphetsByReligionName(this.religionName, this.currentPage, this.tableSize);
    this.getAllProphetsTotal();
  }

  getAllProphetsTotal() {
    this.prophetService.getAllProphetsByReligionName$(this.religionName).subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    )
  }

  getPaginatedProphetsByReligionName(name: string, pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.prophetService.getPaginatedProphetsByReligionName$(name, pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.prophets = result.data.dataRetrieved;
          this.dataSubject.next(result);
          return {
            dataState: DataState.LOADED,
            appData: result,
          };
        }),
        startWith({
          dataState: DataState.LOADING
        }),
        catchError((caughtError: string) => {
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
  }

  async getProphetByID(religionID: number) {
    const result = await this.prophetService.getProphetByID(religionID).toPromise();
    this.selectedProphet = result.data.datumRetrieved;
    console.log(this.selectedProphet);
  }

  saveProphet(prophetForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.prophetService
      .saveProphet$(prophetForm.value) //or dayForm.value as Day or <Day> dayForm.value
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
          prophetForm.resetForm();
          this.isClicked = false;
          this.isTableShown = true;
          this.isLoading.next(false);
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
    this.getAllProphetsTotal();
  }

  deleteProphet(prophet: Prophet) {
    this.appState$ = this.prophetService
      .deleteProphet$(prophet.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((p) =>
                  p.id !== prophet.id //delete the record that matches r.id === race.id
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
    this.getAllProphetsTotal();
  }

  deleteProphets(prophets: Prophet[]) {
    for(let prophet of prophets) {
      this.prophetService.deleteProphet$(prophet.id).subscribe();
    }
  }

  modifyProphet(prophet: Prophet) {
    this.isLoading.next(true);
    this.appState$ = this.prophetService.modifyProphet$(prophet.id, prophet).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(prophet => prophet.id === result.data.dataUpdated.id); //loops through the array and finds the record whose id matches the updated day from the backend
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

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedProphetsByReligionName(this.religionName, this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedProphetsByReligionName(this.religionName, this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for(let religion of this.prophets) {
      religion.isSelected = this.isMasterSelected;
    }
    this.getCheckedProphet();
  }

  isAllSelected() {
    this.isMasterSelected = this.prophets.every(prophet => prophet.isSelected);
    this.getCheckedProphet();
  }

  getCheckedProphet() {
    this.checkedProphets = [];
    for (let checkedProphet of this.prophets) {
      if (checkedProphet.isSelected) {
        this.checkedProphets.push(checkedProphet);
      }
    }
  }

  hasSelected() {
    return this.prophets.some(prophet => prophet.isSelected);
  }

  routeToProphetDetails(prophetID: number, prophetName: string) {
    this.router.navigateByUrl(`/religions/${+this.religionID}/${this.religionName}/prophets/${prophetID}/${prophetName}`);
  }

  routeToReligionDisplay(religionID: number, religionName: string) {
    this.router.navigateByUrl(`/religions/${religionID}/${religionName}`);
  }
}

import {Component, OnInit} from '@angular/core';
import {
  faTrash,
  faEdit,
  faPlusCircle, faCircleArrowLeft
} from '@fortawesome/free-solid-svg-icons';
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {AppState} from "../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {DataState} from "../../../../../enums/data-state";
import {Deity} from "../../../../../interfaces/models/dogma/mythology/deity";
import {DeityService} from "../../../../../services/models/dogma/mythology/deity/deity.service";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-deity',
  templateUrl: './deity.component.html',
  styleUrl: './deity.component.css'
})
export class DeityComponent implements OnInit {
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

  selectedDeity: Deity;
  deities: Deity[];
  checkedDeities: Deity[];

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

  constructor(private deityService: DeityService,
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
    this.getPaginatedDeitiesByReligionName(this.religionName, this.currentPage, this.tableSize);
    this.getAllDeitiesTotal();
  }

  getAllDeitiesTotal() {
    this.deityService.getAllDeitiesByReligionName$(this.religionName).subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    )
  }

  getPaginatedDeitiesByReligionName(name: string, pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.deityService.getPaginatedDeitiesByReligionName$(name, pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.deities = result.data.dataRetrieved;
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

  async getDeityByID(deityID: number) {
    const result = await this.deityService.getDeityByID(deityID).toPromise();
    this.selectedDeity = result.data.datumRetrieved;
    console.log(this.selectedDeity);
  }

  saveDeity(deityForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.deityService
      .saveDeity$(this.religionName, deityForm.value) //or dayForm.value as Day or <Day> dayForm.value
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
          deityForm.resetForm();
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
    this.getAllDeitiesTotal();
  }

  deleteDeity(deity: Deity) {
    this.appState$ = this.deityService
      .deleteDeity$(deity.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((d) =>
                  d.id !== deity.id //delete the record that matches r.id === race.id
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
    this.getAllDeitiesTotal();
  }

  deleteDeities(deities: Deity[]) {
    for(let deity of deities) {
      this.deityService.deleteDeity$(deity.id).subscribe();
    }
  }

  modifyDeity(deity: Deity) {
    this.isLoading.next(true);
    this.appState$ = this.deityService.modifyDeity$(deity.id, deity).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(deity => deity.id === result.data.dataUpdated.id); //loops through the array and finds the record whose id matches the updated day from the backend
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
    this.getPaginatedDeitiesByReligionName(this.religionName, this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedDeitiesByReligionName(this.religionName, this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for(let deity of this.deities) {
      deity.isSelected = this.isMasterSelected;
    }
    this.getCheckedDeity();
  }

  isAllSelected() {
    this.isMasterSelected = this.deities.every(deity => deity.isSelected);
    this.getCheckedDeity();
  }

  getCheckedDeity() {
    this.checkedDeities = [];
    for (let checkedDeity of this.deities) {
      if (checkedDeity.isSelected) {
        this.checkedDeities.push(checkedDeity);
      }
    }
  }

  hasSelected() {
    return this.deities.some(deity => deity.isSelected);
  }

  routeToDeityDetails(prophetID: number, prophetName: string) {
    this.router.navigateByUrl(`/religions/${+this.religionID}/${this.religionName}/deities/${prophetID}/${prophetName}`);
  }

  routeToReligionDisplay(religionID: number, religionName: string) {
    this.router.navigateByUrl(`/religions/${religionID}/${religionName}`);
  }
}

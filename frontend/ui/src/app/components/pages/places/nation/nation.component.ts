import {Component, OnInit, TemplateRef} from '@angular/core';
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
import {Nation} from "../../../../interfaces/models/places/nation";
import {NationService} from "../../../../services/models/places/nation/nation.service";
import {Router} from "@angular/router";
import {NationType} from "../../../../enums/nation-type";
import {GovernanceType} from "../../../../enums/governance-type";
import {Lineage} from "../../../../enums/lineage";

@Component({
  selector: 'app-nation',
  templateUrl: './nation.component.html',
  styleUrl: './nation.component.css'
})
export class NationComponent implements OnInit {
  currentPage:number  = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  nationTypes: NationType;
  governanceTypes: GovernanceType;
  GovernanceType = GovernanceType;
  NationType = NationType;

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  countSubject = new BehaviorSubject<number>(0)
  count$ = this.countSubject.asObservable();

  selectedNation: Nation;
  nations: Nation[];
  checkedNations: Nation[];

  isUpdated: boolean = false;
  isClicked: boolean = false;
  isTableShown: boolean = false;
  isMasterSelected: boolean = false;

  faTrash = faTrash;
  faEdit = faEdit;
  headers = [
    {
      key: 'name',
      value: 'Nation',
    }
  ];

  constructor(private nationService: NationService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getPaginatedNations(this.currentPage, this.tableSize);
  }

  getPaginatedNations(pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.nationService.getPaginatedNations$(pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.nations = result.data.dataRetrieved;
          this.dataSubject.next(result); //stores result in dataSubject to be used in another method or later
          this.countSubject.next(result.data.dataRetrieved.length);
          this.isTableShown = true;
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

  async getNationByID(nationID: number) {
    const result = await this.nationService.getNationByID(nationID).toPromise();
    this.selectedNation = result.data.datumRetrieved;
  }

  saveNation(nationForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.nationService
      .saveNation$(nationForm.value) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.nations = result.data.dataRetrieved;
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
          this.countSubject.next(this.dataSubject.value.data.dataRetrieved.length);
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

  deleteNation(nation: Nation) {
    this.appState$ = this.nationService
      .deleteNation$(nation.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((n) =>
                  n.id !== nation.id //delete the record that matches d.id === day.id
                )
              }
            }
          );
          this.countSubject.next(result.data.dataRetrieved.length);
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

  deleteNations(nations: Nation[]) {
    for(let nation of nations) {
      this.nationService.deleteNation(nation.id).subscribe();
    }
  }

  modifyNation(Nation: Nation) {
    this.isLoading.next(true);
    this.appState$ = this.nationService.modifyNation$(Nation.id, Nation).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(person => person.id === result.data.dataUpdated.id);
        this.dataSubject.value.data.dataRetrieved[index] = result.data.dataUpdated;
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
    this.getPaginatedNations(this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedNations(this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for(let Nation of this.nations) {
      Nation.isSelected = this.isMasterSelected;
    }
    this.getCheckedNation();
  }

  isAllSelected() {
    this.isMasterSelected = this.nations.every(nation => nation.isSelected);
    this.getCheckedNation();
  }

  getCheckedNation() {
    this.checkedNations = [];
    for (let checkedNation of this.nations) {
      if (checkedNation.isSelected) {
        this.checkedNations.push(checkedNation);
      }
    }
  }

  hasSelected() {
    return this.nations.some(nation => nation.isSelected);
  }

  // Convert enum values into an array for iteration
  get nationTypeKeys() {
    return Object.keys(NationType).filter(
      (key) => key !== 'ALL' && isNaN(Number(key))
    );
  }

  get nationTypeValues() {
    return Object.values(this.nationTypes);
  }

  get governanceTypeKeys() {
    return Object.keys(GovernanceType).filter(
      (key) => key !== 'ALL' && isNaN(Number(key))
    );
  }

  get governanceTypeValues() {
    return Object.values(this.governanceTypes);
  }

  protected readonly Object = Object;
}

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

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

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
      value: 'Nation Name',
    }
  ];

  constructor(private nationService: NationService) {
  }

  ngOnInit(): void {
    this.getPaginatedNations(this.currentPage, this.tableSize);
  }

  getPaginatedNations(pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.nationService.getPaginatedNations$(pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.dataSubject.next(result); //stores result in dataSubject to be used in another method or later
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

  getNationByID(nationID: number) {
    this.nationService.getNationByID(nationID).subscribe(result => {
      this.selectedNation = result.data.datumRetrieved;
    })
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

}

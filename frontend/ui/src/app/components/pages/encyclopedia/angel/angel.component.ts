import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, firstValueFrom, map, Observable, of, startWith} from "rxjs";
import {AppState} from "../../../../interfaces/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {DataState} from "../../../../enums/data-state";
import {
  faTrash,
  faEdit,
  faPlusCircle
} from '@fortawesome/free-solid-svg-icons';
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";
import {Angel} from "../../../../interfaces/models/dogma/mythology/angel";
import {AngelService} from "../../../../services/models/dogma/mythology/angel/angel.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-angel',
  templateUrl: './angel.component.html',
  styleUrl: './angel.component.css'
})
export class AngelComponent implements OnInit {
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

  selectedAngel: Angel;
  angels: Angel[];
  checkedAngels: Angel[];

  isUpdated: boolean = false;
  isClicked: boolean = false;
  isTableShown: boolean = false;
  isMasterSelected: boolean = false;

  faTrash = faTrash;
  faEdit = faEdit;
  faPlusCircle = faPlusCircle;
  headers = [
    {
      key: 'name',
      value: 'Name',
    }
  ];

  constructor(private angelService: AngelService,
              private router: Router) {
  }

  ngOnInit() {
    this.getPaginatedAngels(this.currentPage, this.tableSize);
    this.getAllAngelsTotal();
  }

  getAllAngelsTotal() {
    this.angelService.getAllAngels$().subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    )
  }

  getAllAngelsTotalByReligion(religionName: string) {
    this.angelService.getAllAngelsByReligionName$(religionName).subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    )
  }

  getPaginatedAngels(pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.angelService.getPaginatedAngels$(pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.angels = result.data.dataRetrieved;
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

  getPaginatedAngelsByReligionName(name: string, pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.angelService.getPaginatedAngelsByReligionName$(name, pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.angels = result.data.dataRetrieved;
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

  async getAngelByID(angelID: number) {
    const result = await firstValueFrom(this.angelService.getAngelByID(angelID));
    this.selectedAngel = result.data.datumRetrieved;
    console.log(this.selectedAngel);
  }

  saveAngel(angelForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.angelService
      .saveAngel$(angelForm.value) //or dayForm.value as Day or <Day> dayForm.value
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
          angelForm.resetForm();
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
    this.getAllAngelsTotal();
  }

  deleteAngel(angel: Angel) {
    this.appState$ = this.angelService
      .deleteAngel$(angel.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((a) =>
                  a.id !== angel.id
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
    this.getAllAngelsTotal();
  }

  deleteAngels(angels: Angel[]) {
    for(let angel of angels) {
      this.angelService.deleteAngel$(angel.id).subscribe();
    }
  }

  modifyAngel(angel: Angel) {
    this.isLoading.next(true);
    this.appState$ = this.angelService.modifyAngel$(angel.id, angel).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(angel => angel.id === result.data.dataUpdated.id); //loops through the array and finds the record whose id matches the updated day from the backend
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
    this.getPaginatedAngels(this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedAngels(this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for(let religion of this.angels) {
      religion.isSelected = this.isMasterSelected;
    }
    this.getCheckedAngel();
  }

  isAllSelected() {
    this.isMasterSelected = this.angels.every(angel => angel.isSelected);
    this.getCheckedAngel();
  }

  getCheckedAngel() {
    this.checkedAngels = [];
    for (let checkedAngel of this.angels) {
      if (checkedAngel.isSelected) {
        this.checkedAngels.push(checkedAngel);
      }
    }
  }

  hasSelected() {
    return this.angels.some(angel => angel.isSelected);
  }

  routeToAngelDetails(angelID: number, angelName: string) {
    this.router.navigateByUrl(`/encyclopedia/angels/${angelID}/${angelName}`);
  }
}

import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {AppState} from "../../../../interfaces/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {DataState} from "../../../../enums/data-state";
import {
  faTrash,
  faEdit,
  faPlusCircle
} from '@fortawesome/free-solid-svg-icons';
import {ReligionService} from "../../../../services/models/dogma/religion/religion.service";
import {catchError} from "rxjs/operators";
import {FormArray, FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {Religion} from "../../../../interfaces/models/dogma/religion";
import {Router} from "@angular/router";

@Component({
  selector: 'app-religion',
  templateUrl: './religion.component.html',
  styleUrl: './religion.component.css'
})
export class ReligionComponent implements OnInit {
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

  religions: Religion[] = [];
  checkedReligions: Religion[] = [];
  selectedReligion: Religion;

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

  religionForm: FormGroup;

  constructor(private religionService: ReligionService,
              private router: Router,
              private formBuilder: FormBuilder) {
    this.religionForm = this.initializeReligionForm();
  }

  ngOnInit() {
    this.getPaginatedReligions(this.currentPage, this.tableSize);
    this.getAllReligionsTotal();
  }

  initializeReligionForm() {
    return this.formBuilder.group({
      name: ['', Validators.required],
      nations: this.formBuilder.array([])
      //nations: this.formBuilder.array([], Validators.required)  // FormArray for dynamic list of nations
    });
  }

  // Getter for easy access to the nations FormArray
  get nations(): FormArray {
    return this.religionForm.get('nations') as FormArray;
  }

  // Method to add a new nation FormControl to the nations FormArray
  addNation(): void {
    this.nations.push(this.formBuilder.control('', Validators.required));
  }

  // Method to remove a nation FormControl from the nations FormArray
  removeNation(index: number): void {
    this.nations.removeAt(index);
  }

  resetForm() {
    this.religionForm.reset({
      name: '',  // default value for religion name
      nations: []  // default value for nations (empty array)
    });
  }

  getAllReligionsTotal() {
    this.religionService.getAllReligionsCount$.subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    )
  }

  getPaginatedReligions(pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.religionService.getPaginatedReligions$(pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.religions = result.data.dataRetrieved;
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

  async getReligionByID(religionID: number) {
    const result = await this.religionService.getReligionByID(religionID).toPromise();
    this.selectedReligion = result.data.datumRetrieved;
    console.log(this.selectedReligion);
    this.religionForm = this.formBuilder.group({
      name: [this.selectedReligion.name, Validators.required],
      nations: this.formBuilder.array(
        this.selectedReligion.nations ? this.selectedReligion.nations.map(nation => this.formBuilder.control(nation)) : []
      ),
    });
  }

  saveReligion(religionForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.religionService
      .saveReligion$(religionForm.value) //or dayForm.value as Day or <Day> dayForm.value
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
          religionForm.resetForm();
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
    this.getAllReligionsTotal();
  }

  saveReligionForm(religionForm: FormGroup) {
    this.isLoading.next(true);
    this.appState$ = this.religionService
      .saveReligion$(religionForm.value) //or dayForm.value as Day or <Day> dayForm.value
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
          this.resetForm();
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
    this.getAllReligionsTotal();
  }

  deleteReligion(religion: Religion) {
    this.appState$ = this.religionService
      .deleteReligion$(religion.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((r) =>
                  r.id !== religion.id //delete the record that matches r.id === race.id
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
    this.getAllReligionsTotal();
  }

  deleteReligions(religions: Religion[]) {
    for(let religion of religions) {
      this.religionService.deleteReligion$(religion.id).subscribe();
    }
  }

  modifyReligion(religion: Religion) {
    this.isLoading.next(true);
    this.appState$ = this.religionService.modifyReligion$(religion.id, religion).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(religion => religion.id === result.data.dataUpdated.id); //loops through the array and finds the record whose id matches the updated day from the backend
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

  modifyReligionForm(religionID: number, religion: Religion) {
    this.isLoading.next(true);
    this.appState$ = this.religionService.modifyReligion$(religionID, religion).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(religion => religion.id === result.data.dataUpdated.id); //loops through the array and finds the record whose id matches the updated day from the backend
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

  updateReligionForm(religionID: number, religion: Religion) {
    this.isLoading.next(true);
    this.appState$ = this.religionService.updateReligion$(religionID, religion).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(religion => religion.id === result.data.dataUpdated.id); //loops through the array and finds the record whose id matches the updated day from the backend
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
    this.getPaginatedReligions(this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedReligions(this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for(let religion of this.religions) {
      religion.isSelected = this.isMasterSelected;
    }
    this.getCheckedReligion();
  }

  isAllSelected() {
    this.isMasterSelected = this.religions.every(religion => religion.isSelected);
    this.getCheckedReligion();
  }

  getCheckedReligion() {
    this.checkedReligions = [];
    for (let checkedReligion of this.religions) {
      if (checkedReligion.isSelected) {
        this.checkedReligions.push(checkedReligion);
      }
    }
  }

  hasSelected() {
    return this.religions.some(religion => religion.isSelected);
  }

  routeToReligionDetails(religionID: number, name: string) {
    this.router.navigateByUrl(`/religions/${religionID}/${name}`);
  }
}

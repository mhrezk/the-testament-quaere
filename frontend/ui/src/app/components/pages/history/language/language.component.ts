import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {Race} from "../../../../interfaces/models/history/race";
import {AppState} from "../../../../interfaces/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {DataState} from "../../../../enums/data-state";
import {
  faTrash,
  faEdit,
} from '@fortawesome/free-solid-svg-icons';
import {LanguageService} from "../../../../services/models/history/language/language.service";
import {Router} from "@angular/router";
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";
import {Language} from "../../../../interfaces/models/history/language";

@Component({
  selector: 'app-language',
  templateUrl: './language.component.html',
  styleUrl: './language.component.css'
})
export class LanguageComponent implements OnInit {
  currentPage: number  = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  countSubject = new BehaviorSubject<number>(0)
  count$ = this.countSubject.asObservable();

  selectedLanguage: Language;
  languages: Language[] = [];
  checkedLanguages: Language[] = [];

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
      value: 'Language',
    },
    // {
    //   key: 'description',
    //   value: 'Description'
    // }
  ];

  constructor(private languageService: LanguageService,
              private router: Router) {}

  ngOnInit() {
    this.getPaginatedLanguages(this.currentPage, this.tableSize);
    //this.getAllLanguagesTotal();
  }

  getAllLanguagesTotal() {
    this.languageService.getAllLanguagesCount().subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
        console.log(this.countSubject.value);
      }
    )
  }

  getPaginatedLanguages(pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.languageService.getPaginatedLanguages$(pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.languages = result.data.dataRetrieved;
          this.countSubject.next(result.data.dataRetrieved.length);
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

  getLanguageByID(languageID: number) {
    this.languageService.getLanguageByID(languageID).subscribe(result => {
      this.selectedLanguage = result.data.datumRetrieved;
    })
  }

  async doesLanguageNameExist(language: string) {
    const result = await this.languageService.doesLanguageNameExist(language).toPromise();
    this.doesExist.next(result);
    console.log(this.doesExist.value.data.datumRetrieved);
    return this.doesExist.value.data.datumRetrieved;
  }

  async saveLanguage(languageForm: NgForm) {
    if(await this.doesLanguageNameExist(languageForm.value.name)) {
      this.enteredName = languageForm.value.name;
      this.showError = true;
      this.isClicked = false;
    } else {
      this.isLoading.next(true);
      this.appState$ = this.languageService
        .saveLanguage$(languageForm.value) //or dayForm.value as Day or <Day> dayForm.value
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
            languageForm.resetForm();
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
      this.getAllLanguagesTotal();
    }
  }

  deleteLanguage(language: Language) {
    this.appState$ = this.languageService
      .deleteLanguage$(language.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((l) =>
                  l.id !== language.id //delete the record that matches r.id === race.id
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
    this.getAllLanguagesTotal();
  }

  async modifyLanguage(language: Language) {
    if(await this.doesLanguageNameExist(language.name)) {
      this.enteredName = language.name;
      this.showError = true;
      this.isUpdated = false;
    } else {
      this.isLoading.next(true);
      this.appState$ = this.languageService.modifyLanguage$(language.id, language).pipe(
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

  deleteLanguages(languages: Language[]) {
    for(let language of languages) {
      this.languageService.deleteLanguage(language.id).subscribe();
    }
    this.getAllLanguagesTotal();
  }

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedLanguages(this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedLanguages(this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for(let language of this.languages) {
      language.isSelected = this.isMasterSelected;
    }
    this.getCheckedLanguages();
  }

  isAllSelected() {
    this.isMasterSelected = this.languages.every(language => language.isSelected);
    this.getCheckedLanguages();
  }

  getCheckedLanguages() {
    this.checkedLanguages = [];
    for(let checkedLanguage of this.languages) {
      if(checkedLanguage.isSelected) {
        this.checkedLanguages.push(checkedLanguage);
      }
    }
  }

  hasSelected() {
    return this.languages.some(language => language.isSelected);
  }

  routeToLanguages(languageID: number, languageName: string) {
    this.languageService.setLanguageDetails(languageID, languageName);
    this.router.navigateByUrl(`languages/${languageID}/${languageName}`)
  }
}

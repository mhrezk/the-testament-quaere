import {Component, OnInit} from '@angular/core';
import {LetterService} from "../../../../services/models/history/letter/letter.service";
import {ActivatedRoute, Router} from "@angular/router";
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {DataState} from "../../../../enums/data-state";
import {catchError} from "rxjs/operators";
import {AppState} from "../../../../interfaces/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {Letter} from "../../../../interfaces/models/history/letter";
import {NgForm} from "@angular/forms";
import {
  faCircleArrowLeft,
  faTrash,
  faEdit,
} from '@fortawesome/free-solid-svg-icons';
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {Race} from "../../../../interfaces/models/history/race";

@Component({
  selector: 'app-letter',
  templateUrl: './letter.component.html',
  styleUrl: './letter.component.css'
})
export class LetterComponent implements OnInit {
  currentPage: number  = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  faCircleArrowLeft = faCircleArrowLeft;
  faTrash = faTrash;
  faEdit = faEdit;

  languageName: string;
  languageID: string;
  alphabetSize: string;

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  lettersSubject = new BehaviorSubject<Letter[]>([]);
  letters$ = this.lettersSubject.asObservable();

  letters: Letter[];
  checkedLetters: Letter[];

  selectedLetter: Letter;

  isTableShown: boolean = false;
  isUpdated: boolean = false;
  isClicked: boolean = false;
  isEditingDescription: boolean = false;
  isDisplayed: boolean = false;
  isMasterSelected: boolean = false;
  showError: boolean = false;

  enteredName: string;

  errorMessage: string = "already exists in the database! Duplicate entries are disallowed!";

  countSubject = new BehaviorSubject<number>(0);
  count$ = this.countSubject.asObservable();

  constructor(private letterService: LetterService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.languageID = result.get("id");
        this.languageName = result.get("name");
        this.alphabetSize = result.get("size");
      }
    );
    this.getLettersByLanguageName(this.languageName);
  }

  getLettersByLanguageName(languageName: string) {
    this.isTableShown = true;
    this.appState$ = this.letterService.getLettersByLanguageName$(languageName)
      .pipe(
        map((result) => {
          this.letters = result.data.dataRetrieved;
          this.dataSubject.next(result);
          this.countSubject.next(result.data.dataRetrieved.length);
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

  async getLetterByID(letterID: number) {
    const result = await this.letterService.getLetterByID(letterID).toPromise();
    this.selectedLetter = result.data.datumRetrieved;
    console.log(this.selectedLetter);
  }

  saveLetter(letterForm: NgForm) {
    console.log("Button Pressed!");
    this.isLoading.next(true);
    this.appState$ = this.letterService
      .saveLetter$(letterForm.value, this.languageName) //or dayForm.value as Day or <Day> dayForm.value
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
          this.countSubject.next(this.dataSubject.value.data.dataRetrieved.length);
          letterForm.resetForm();
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

  deleteLetter(letter: Letter) {
    this.appState$ = this.letterService
      .deleteLetter$(letter.id, this.languageName) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((l) =>
                  l.id !== letter.id //delete the record that matches r.id === race.id
                )
              }
            }
          );
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
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
  }

  deleteLetters(letters: Letter[]) {
    for(let letter of letters) {
      this.letterService.deleteLetter(letter.id, this.languageName).subscribe();
    }
  }

  modifyLetter(letter: Letter) {
    this.isLoading.next(true);
    this.appState$ = this.letterService.modifyLetter$(letter.id, letter).pipe(
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

  updateDescription(letter: Letter) {
    this.letterService.updateLetter(letter.id, letter).subscribe();
    this.isEditingDescription = false;
    this.isDisplayed = false;
    this.isTableShown = true;
  }

  onTableDataChange(event: any) {
    this.currentPage = event;
    //this.getLetters();
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    //this.getLetters();
  }

  checkUncheckAll() {
    for(let letter of this.letters) {
      letter.isSelected = this.isMasterSelected;
    }
    this.getCheckedLetters();
  }

  isAllSelected() {
    this.isMasterSelected = this.letters.every(letter => letter.isSelected);
    this.getCheckedLetters();
  }

  getCheckedLetters() {
    this.checkedLetters = [];
    for(let checkedLetter of this.letters) {
      if(checkedLetter.isSelected) {
        this.checkedLetters.push(checkedLetter);
      }
    }
  }

  hasSelected() {
    return this.letters.some(letter => letter.isSelected);
  }

  routeToLanguage(languageID: number, languageName: string) {
    this.router.navigateByUrl(`/languages/${languageID}/${languageName}`);
  }
}

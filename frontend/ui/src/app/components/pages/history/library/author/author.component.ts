import {Component, OnInit} from '@angular/core';
import {
  faTrash,
  faEdit,
} from '@fortawesome/free-solid-svg-icons';
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {Author} from "../../../../../interfaces/models/history/library/author";
import {AppState} from "../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {DataState} from "../../../../../enums/data-state";
import {AuthorService} from "../../../../../services/models/history/library/author/author.service";
import {Router} from "@angular/router";
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrl: './author.component.css'
})
export class AuthorComponent implements OnInit {
  currentPage: number = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  countSubject = new BehaviorSubject<number>(0)
  count$ = this.countSubject.asObservable();

  selectedAuthor: Author;
  authors: Author[];
  checkedAuthors: Author[];

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  isTableShown: boolean = false;
  isUpdated: boolean = false;
  isClicked: boolean = false;
  isDisplaying: boolean = false;
  isMasterSelected: boolean = false;

  faTrash = faTrash;
  faEdit = faEdit;

  headers = [
    {
      key: 'name',
      value: 'Name',
    },
    {
      key: 'penName',
      value: 'Pen Name',
    },
    {
      key: 'gender',
      value: 'Gender',
    }
  ];

  constructor(private authorService: AuthorService, private router: Router) {
  }

  ngOnInit() {
    this.getPaginatedAuthors(this.currentPage, this.tableSize);
    this.getAllAuthorsTotal();
  }

  getAllAuthorsTotal() {
    this.authorService.getAllAuthorsCount().subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    )
    console.log(this.countSubject.value);
  }

  getPaginatedAuthors(pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.authorService.getPaginatedAuthors$(pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.authors = result.data.dataRetrieved;
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

  getAuthorByID(authorID: number) {
    this.authorService.getAuthorByID(authorID).subscribe(result => {
      this.selectedAuthor = result.data.datumRetrieved;
    })
  }

  saveAuthor(authorForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.authorService
      .saveAuthor$(authorForm.value) //or dayForm.value as Day or <Day> dayForm.value
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
          authorForm.resetForm();
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
    this.getAllAuthorsTotal();
  }

  deleteAuthor(author: Author) {
    this.appState$ = this.authorService
      .deleteAuthor$(author.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((a) =>
                  a.id !== author.id //delete the record that matches r.id === race.id
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
    this.getAllAuthorsTotal();
  }

  modifyAuthor(author: Author) {
    this.isLoading.next(true);
    this.appState$ = this.authorService.modifyAuthor$(author.id, author).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(author => author.id === result.data.dataUpdated.id); //loops through the array and finds the record whose id matches the updated day from the backend
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

  deleteAuthors(authors: Author[]) {
    for (let author of authors) {
      this.authorService.deleteAuthor$(author.id).subscribe();
    }
    this.getAllAuthorsTotal();
  }

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedAuthors(this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedAuthors(this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for (let author of this.authors) {
      author.isSelected = this.isMasterSelected;
    }
    this.getCheckedAuthors();
  }

  isAllSelected() {
    this.isMasterSelected = this.authors.every(race => race.isSelected);
    this.getCheckedAuthors();
  }

  getCheckedAuthors() {
    this.checkedAuthors = [];
    for (let checkedAuthor of this.authors) {
      if (checkedAuthor.isSelected) {
        this.checkedAuthors.push(checkedAuthor);
      }
    }
  }

  hasSelected() {
    return this.authors.some(author => author.isSelected);
  }

  routeToDisplay(authorID: number, firstName: string, lastName: string) {
    this.router.navigateByUrl(`/author/${authorID}/${firstName}/${lastName}`);
  }
}

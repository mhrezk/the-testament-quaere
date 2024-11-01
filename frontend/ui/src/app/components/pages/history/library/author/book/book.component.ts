import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {Book} from "../../../../../../interfaces/models/history/library/book";
import {AppState} from "../../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../../interfaces/custom-response";
import {DataState} from "../../../../../../enums/data-state";
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {BookService} from "../../../../../../services/models/history/library/book/book.service";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrl: './book.component.css'
})
export class BookComponent implements OnInit {
  currentPage: number = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  countSubject = new BehaviorSubject<number>(0)
  count$ = this.countSubject.asObservable();

  selectedBook: Book;
  books: Book[];
  checkedBooks: Book[];

  authorID: string;
  authorFirstName: string;
  authorLastName: string;

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
  faCircleArrowLeft = faCircleArrowLeft;

  headers = [
    {
      key: 'name',
      value: 'Book Name',
    }
  ];

  constructor(private bookService: BookService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.authorID = result.get("id");
        this.authorFirstName = result.get("firstName");
        this.authorLastName = result.get("lastName");
      }
    );
    this.getPaginatedBooksByAuthorName(+this.authorID, this.authorFirstName, this.authorLastName, this.currentPage, this.tableSize);
    this.getAllBooksByAuthorNameTotal(+this.authorID, this.authorFirstName, this.authorLastName);
  }

  getAllBooksByAuthorNameTotal(authorID: number, firstName: string, lastName: string) {
    this.bookService.getAllBooksByAuthorNameCount(authorID, firstName, lastName).subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    )
    console.log(this.countSubject.value);
  }

  getPaginatedBooksByAuthorName(authorID: number, firstName: string, lastName: string, pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.bookService.getPaginatedBooksByAuthorName$(authorID, firstName, lastName, pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.books = result.data.dataRetrieved;
          this.dataSubject.next(result);
          this.bookService.setBookCount(result.data.dataRetrieved.length);
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

  getBookByID(bookID: number) {
    this.bookService.getBookByID(bookID).subscribe(result => {
      this.selectedBook = result.data.datumRetrieved;
    })
  }

  saveBook(bookForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.bookService
      .saveBook$(+this.authorID, this.authorFirstName, this.authorLastName, bookForm.value) //or dayForm.value as Day or <Day> dayForm.value
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
          bookForm.resetForm();
          this.bookService.setBookCount(this.dataSubject.value.data.dataRetrieved.length);
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
    this.getAllBooksByAuthorNameTotal(+this.authorID, this.authorFirstName, this.authorLastName);
  }

  deleteBook(book: Book) {
    this.appState$ = this.bookService
      .deleteBook$(book.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((b) =>
                  b.id !== book.id //delete the record that matches r.id === race.id
                )
              }
            }
          );
          this.bookService.setBookCount(this.dataSubject.value.data.dataRetrieved.length);
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
    this.getAllBooksByAuthorNameTotal(+this.authorID, this.authorFirstName, this.authorLastName);
  }

  modifyBook(book: Book) {
    this.isLoading.next(true);
    this.appState$ = this.bookService.modifyBook$(book.id, +this.authorID, book).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(book => book.id === result.data.dataUpdated.id); //loops through the array and finds the record whose id matches the updated day from the backend
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

  deleteBooks(books: Book[]) {
    for (let book of books) {
      this.bookService.deleteBook$(book.id).subscribe();
    }
    this.getAllBooksByAuthorNameTotal(+this.authorID, this.authorFirstName, this.authorLastName);
    this.bookService.setBookCount(this.countSubject.value);
  }

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedBooksByAuthorName(+this.authorID, this.authorFirstName, this.authorLastName, this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedBooksByAuthorName(+this.authorID, this.authorFirstName, this.authorLastName, this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for (let book of this.books) {
      book.isSelected = this.isMasterSelected;
    }
    this.getCheckedBooks();
  }

  isAllSelected() {
    this.isMasterSelected = this.books.every(book => book.isSelected);
    this.getCheckedBooks();
  }

  getCheckedBooks() {
    this.checkedBooks = [];
    for (let checkedAuthor of this.books) {
      if (checkedAuthor.isSelected) {
        this.checkedBooks.push(checkedAuthor);
      }
    }
  }

  hasSelected() {
    return this.books.some(book => book.isSelected);
  }

  routeToAuthorDisplay(authorID: number, firstName: string, lastName: string) {
    this.router.navigateByUrl(`/author/${authorID}/${firstName}/${lastName}`);
  }

  routeToBookDisplay(authorID: number, authorFirstName: string, authorLastName: string, bookID: number) {
    this.router.navigateByUrl(`author/${authorID}/${authorFirstName}/${authorLastName}/books/${bookID}`);
  }
}

import {Component, OnInit} from '@angular/core';
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {Chapter} from "../../../../../../../interfaces/models/history/library/chapter";
import {AppState} from "../../../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../../../interfaces/custom-response";
import {DataState} from "../../../../../../../enums/data-state";
import {ChapterService} from "../../../../../../../services/models/history/library/chapter/chapter.service";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";
import {Book} from "../../../../../../../interfaces/models/history/library/book";

@Component({
  selector: 'app-chapter',
  templateUrl: './chapter.component.html',
  styleUrl: './chapter.component.css'
})
export class ChapterComponent implements OnInit {
  currentPage: number = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  countSubject = new BehaviorSubject<number>(0)
  count$ = this.countSubject.asObservable();

  chapters: Chapter[];
  selectedChapter: Chapter;
  checkedChapters: Chapter[];

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  authorID: string;
  authorFirstName: string;
  authorLastName: string;
  bookID: string;
  bookName: string;

  isTableShown: boolean = false;
  isUpdated: boolean = false;
  isClicked: boolean = false;
  isDisplaying: boolean = false;
  isMasterSelected: boolean = false;
  atLeastOneRequired: boolean = false;

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  headers = [
    {
      key: 'name',
      value: 'Chapter Name',
    },
    {
      key: 'number',
      value: 'Chapter Number',
    }
  ];

  constructor(private chapterService: ChapterService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.bookID = result.get("bookID");
        this.bookName = result.get("name");
        this.authorID = result.get("id");
        this.authorFirstName = result.get("firstName");
        this.authorLastName = result.get("lastName");
      }
    )
    this.getPaginatedChaptersByBookName(this.bookName, this.currentPage, this.tableSize);
    this.getAllChaptersByBookNameTotal(this.bookName);
  }

  getAllChaptersByBookNameTotal(bookName: string) {
    this.chapterService.getAllChaptersByBookNameCount(bookName).subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    )
    console.log(this.countSubject.value);
  }

  getPaginatedChaptersByBookName(bookName: string, pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.chapterService.getPaginatedChaptersByBookName$(bookName, pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.chapters = result.data.dataRetrieved;
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

  getChapterByID(chapterID: number) {
    this.chapterService.getChapterByID(chapterID).subscribe(result => {
      this.selectedChapter = result.data.datumRetrieved;
    })
  }

  saveChapter(chapterForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.chapterService
      .saveChapter$(chapterForm.value, this.bookName)
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
          chapterForm.resetForm();
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
    this.getAllChaptersByBookNameTotal(this.bookName);
  }

  deleteChapter(chapter: Chapter) {
    this.appState$ = this.chapterService
      .deleteChapter$(chapter.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((c) =>
                  c.id !== chapter.id
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
    this.getAllChaptersByBookNameTotal(this.bookName);
  }

  modifyChapter(chapter: Chapter) {
    this.isLoading.next(true);
    this.appState$ = this.chapterService.modifyChapter$(chapter.id, chapter).pipe(
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

  deleteChapters(chapters: Chapter[]) {
    for (let chapter of chapters) {
      this.chapterService.deleteChapter$(chapter.id).subscribe();
    }
    this.getAllChaptersByBookNameTotal(this.bookName);
  }

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedChaptersByBookName(this.bookName, this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedChaptersByBookName(this.bookName, this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for (let chapter of this.chapters) {
      chapter.isSelected = this.isMasterSelected;
    }
    this.getCheckedChapters();
  }

  isAllSelected() {
    this.isMasterSelected = this.chapters.every(chapter => chapter.isSelected);
    this.getCheckedChapters();
  }

  getCheckedChapters() {
    this.checkedChapters = [];
    for (let checkedChapter of this.chapters) {
      if (checkedChapter.isSelected) {
        this.checkedChapters.push(checkedChapter);
      }
    }
  }

  hasSelected() {
    return this.chapters.some(chapter => chapter.isSelected);
  }

  routeToBookDisplay(bookID: number) {
    this.router.navigateByUrl(`author/${+this.authorID}/${this.authorFirstName}/${this.authorLastName}/books/${bookID}`);
  }

  routeToChapterDisplay(chapterID: number) {
    this.router.navigateByUrl(`author/${+this.authorID}/${this.authorFirstName}/${this.authorLastName}/books/book/${+this.bookID}/${this.bookName}/chapters/chapter/${chapterID}`);
  }

  // validateFields() {
  //   this.atLeastOneRequired = !(this.field1 || this.field2);
  // }
  //
  // onSubmit(form: any) {
  //   if (!this.atLeastOneRequired) {
  //     console.log("Form Submitted", form.value);
  //   }
  // }
}

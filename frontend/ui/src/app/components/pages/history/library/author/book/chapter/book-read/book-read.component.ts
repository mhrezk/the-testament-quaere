import {Component, OnInit} from '@angular/core';
import {ChapterService} from "../../../../../../../../services/models/history/library/chapter/chapter.service";
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {DataState} from "../../../../../../../../enums/data-state";
import {catchError} from "rxjs/operators";
import {Chapter} from "../../../../../../../../interfaces/models/history/library/chapter";
import {AppState} from "../../../../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../../../../interfaces/custom-response";
import {ActivatedRoute, Router} from "@angular/router";
import {faCircleArrowLeft, faEdit, faTrash} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-book-read',
  templateUrl: './book-read.component.html',
  styleUrl: './book-read.component.css'
})
export class BookReadComponent implements OnInit {
  currentPage: number = 1;
  tableSize: number = 1;
  count: number = 0;

  countSubject = new BehaviorSubject<number>(0)
  count$ = this.countSubject.asObservable();

  chapters: Chapter[];
  selectedChapter: Chapter;
  checkedChapters: Chapter[];

  faCircleArrowLeft = faCircleArrowLeft;
  faEdit = faEdit;
  faTrash = faTrash;

  authorID: string;
  authorFirstName: string;
  authorLastName: string;
  bookID: string;
  bookName: string;

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

  constructor(private chapterService: ChapterService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.bookID = result.get("bookID");
        this.bookName = result.get("name");
        this.authorID = result.get("id");
        this.authorFirstName = result.get("firstName");
        this.authorLastName = result.get("lastName");
      }
    );
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
    //this.isTableShown = true;
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

  routeToBookDisplay(bookID: number) {
    this.router.navigateByUrl(`author/${+this.authorID}/${this.authorFirstName}/${this.authorLastName}/books/${bookID}`);
  }

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedChaptersByBookName(this.bookName, this.currentPage, this.tableSize);
  }
}

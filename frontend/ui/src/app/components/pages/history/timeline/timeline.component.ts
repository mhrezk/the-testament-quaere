import {Component, OnInit} from '@angular/core';
import {DataState} from "../../../../enums/data-state";
import {faTrash, faEdit} from "@fortawesome/free-solid-svg-icons";
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {AppState} from "../../../../interfaces/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {TimelineService} from "../../../../services/models/history/timeline/timeline.service";
import {catchError} from "rxjs/operators";
import {Timeline} from "../../../../interfaces/models/history/timeline";
import {NgForm} from "@angular/forms";
import {Person} from "../../../../interfaces/models/society/person";

@Component({
  selector: 'app-timeline',
  templateUrl: './timeline.component.html',
  styleUrl: './timeline.component.css'
})
export class TimelineComponent implements OnInit {
  currentPage: number  = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  selectedTimeline: Timeline;
  timelines: Timeline[];
  checkedTimelines: Timeline[];

  isClicked: boolean = false;
  isUpdated: boolean = false;
  isTableShown: boolean = false;
  isMasterSelected: boolean = false;

  faTrash = faTrash;
  faEdit = faEdit;

  headers = [
    {
      key: 'name',
      value: 'Timeline Name',
    },
    {
      key: 'timeFrame',
      value: 'Time Frame',
    }
    // {
    //   key: 'beginningYear',
    //   value: 'Beginning Year',
    // },
    // {
    //   key: 'endingYear',
    //   value: 'Ending Year',
    // },
    // {
    //   key: 'yearAbbreviation',
    //   value: 'Year Abbreviation',
    // }
  ];

  constructor(private timelineService: TimelineService) {
  }

  ngOnInit() {
    this.getPaginatedTimelines(this.currentPage, this.tableSize);
    this.getAllTimelineTotal();
  }

  getAllTimelineTotal() {
    this.timelineService.getAllTimelinesCount().subscribe(
      result => {
        this.count = result.data.datumRetrieved;
      }
    )
  }

  getPaginatedTimelines(pageNumber: number, pageSize: number) {
    this.appState$ = this.timelineService.getPaginatedTimelines$(pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.timelines = result.data.dataRetrieved;
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

  getTimelineByID(timelineID: number) {
    this.timelineService.getTimelineByID(timelineID).subscribe(result => {
      this.selectedTimeline = result.data.datumRetrieved;
    });
  }

  saveTimeline(timelineForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.timelineService
      .saveTimeline$(timelineForm.value) //or dayForm.value as Day or <Day> dayForm.value
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
    window.location.reload();
  }

  modifyTimeline(timeline: Timeline) {
    this.isLoading.next(true);
    this.appState$ = this.timelineService.modifyTimeline$(timeline.id, timeline).pipe(
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

  deleteTimeline(timeline: Timeline) {
    this.appState$ = this.timelineService
      .deleteTimeline$(timeline.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((t) =>
                  t.id !== timeline.id
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

  deleteTimelines(timelines: Timeline[]) {
    for(let timeline of timelines) {
      this.timelineService.deleteTimeline(timeline.id).subscribe();
    }
    window.location.reload();
  }

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedTimelines(this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedTimelines(this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for(let timeline of this.timelines) {
      timeline.isSelected = this.isMasterSelected;
    }
    this.getCheckedPeople();
  }

  isAllSelected() {
    this.isMasterSelected = this.timelines.every(person => person.isSelected);
    this.getCheckedPeople();
  }

  getCheckedPeople() {
    this.checkedTimelines = [];
    for(let checkedPerson of this.timelines) {
      if(checkedPerson.isSelected) {
        this.checkedTimelines.push(checkedPerson);
      }
    }
  }

  hasSelected() {
    return this.timelines.some(timeline => timeline.isSelected);
  }
}

import {Component, Input, OnInit} from '@angular/core';
import {faTrash, faEdit, faPlusCircle} from "@fortawesome/free-solid-svg-icons";
import {Event} from "../../../../../interfaces/models/history/event";
import {EventService} from "../../../../../services/models/history/event/event.service";
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {AppState} from "../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {DataState} from "../../../../../enums/data-state";
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";
import {TimelineService} from "../../../../../services/models/history/timeline/timeline.service";
import {Router} from "@angular/router";
import {result} from "lodash";

@Component({
  selector: 'app-timeline-display',
  templateUrl: './timeline-display.component.html',
  styleUrl: './timeline-display.component.css'
})
export class TimelineDisplayComponent implements OnInit {
  timeline: string;

  faTrash = faTrash;
  faEdit = faEdit;
  faPlusCircle = faPlusCircle;

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  isClicked: boolean = false;
  isUpdated: boolean = false;
  isEventShown: boolean = false;
  isNodeAdded: boolean = false;

  specifiedIndex: number = 0;

  events: Event[];
  selectedEvent: Event;

  constructor(private eventService: EventService,
              private timelineService: TimelineService,
              private router: Router) {}

  ngOnInit() {
    this.timelineService.getName$.subscribe(result => this.timeline = result);
    this.getEventsOfTimeline(this.timeline);
  }

  getEventsOfTimeline(name: string) {
    this.appState$ = this.eventService.getEventsOfTimeline$(name)
      .pipe(
        map((result) => {
          this.events = result.data.dataRetrieved;
          this.dataSubject.next(result); //stores result in dataSubject to be used in another method or later
          this.isEventShown = true;
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

  getEventByID(id: number) {
    this.eventService.getEventByID(id).subscribe(result => {
      this.selectedEvent = result.data.datumRetrieved;
    })
  }

  saveEvent(eventForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.eventService.saveEvent$(eventForm.value, this.timeline).pipe(
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
        this.isEventShown = true;
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

  saveEventAtParticularIndex(eventForm: NgForm, index: number) {
    this.isLoading.next(true);

    // Check if the index is greater than the array length and correct it
    const currentEvents = [...this.dataSubject.value.data.dataRetrieved];
    //const validIndex = index >= currentEvents.length ? currentEvents.length : index;

    this.appState$ = this.eventService.saveEvent$(eventForm.value, this.timeline).pipe(
      map((result) => {
        // Insert the new event at the correct index
        currentEvents.splice(index, 0, result.data.dataSaved);

        // Update the state with the modified events array
        this.dataSubject.next({
          ...result,
          data: {
            dataRetrieved: currentEvents, // Updated array
          },
        });
        this.isNodeAdded = false;
        this.isEventShown = true;
        this.isLoading.next(false);
        return {
          dataState: DataState.LOADED,
          appData: this.dataSubject.value,
        };
      }),
      startWith({
        dataState: DataState.LOADED,
        appData: this.dataSubject.value, // Begin with pre-loaded data
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

  modifyEvent(event: Event) {
    this.isLoading.next(true);
    this.appState$ = this.eventService.modifyEvent$(event.id, event).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(person => person.id === result.data.dataUpdated.id);
        this.dataSubject.value.data.dataRetrieved[index] = result.data.dataUpdated;
        this.isUpdated = false;
        this.isEventShown = true;
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

  deleteEvent(event: Event) {
    this.appState$ = this.eventService
      .deleteEvent$(event.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((e) =>
                  e.id !== event.id
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

  refreshAndRoute(timeline: string) {
    this.router.navigateByUrl(`/timeline-display/${timeline}`);
  }
}

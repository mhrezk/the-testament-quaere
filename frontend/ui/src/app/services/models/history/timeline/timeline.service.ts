import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Timeline} from "../../../../interfaces/models/history/timeline";

@Injectable({
  providedIn: 'root'
})
export class TimelineService {
  private baseURL: string = `${environment.API_URL}/history`;

  constructor(private http: HttpClient) { }

  getPaginatedTimelines$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/timelines?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getTimelineByID(timelineID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/timeline/${timelineID}`);
  }

  getAllTimelinesCount() {
    return this.http.get<CustomResponse>(`${this.baseURL}/timelines/all/count`)
  }

  saveTimeline$ = (timeline: Timeline) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/timeline`, timeline)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyTimeline$ = (timelineID: number, timeline: Timeline) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/timeline/${timelineID}`, timeline)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  deleteTimeline$ = (timelineID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/timeline/${timelineID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public deleteTimeline(timelineID: number): Observable<CustomResponse> {
    return this.http.delete<CustomResponse>(`${this.baseURL}/delete/timeline/${timelineID}`);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

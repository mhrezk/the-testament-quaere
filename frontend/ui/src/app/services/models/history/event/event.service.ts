import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Event} from "../../../../interfaces/models/history/event";

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private baseURL: string = `${environment.API_URL}/history`;

  constructor(private http: HttpClient) { }

  getPaginatedEvents$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/events?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  saveEvent$ = (event: Event) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/event`, event)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyEvent$ = (eventID: number, event: Event) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/event/${eventID}`, event)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  deleteEvent$ = (eventID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/event/${eventID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

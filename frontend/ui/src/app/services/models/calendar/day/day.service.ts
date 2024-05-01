import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {tap, catchError} from "rxjs/operators";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {environment} from "../../../../../environments/environment";
import {Day} from "../../../../interfaces/models/calendar/day";

@Injectable({
  providedIn: 'root'
})
export class DayService {
  private baseURL: string = `${environment.API_URL}/calendar`;

  constructor(private http: HttpClient) {}

  //Reactive (Dynamic) Observables
  getPaginatedDays$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/days?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getDays$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/days/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getDayByID$ = (dayID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/day/${dayID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getDayByID(dayID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/day/${dayID}`);
  }

  saveDay$ = (day: Day) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/day`, day)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateDay$ = (dayID: number, day: Day) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/day/${dayID}`, day)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyDay$ = (dayID: number, day: Day) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${dayID}`, day)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyDay(day: Day): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/day`, day);
  }

  deleteDay$ = (dayID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/day/${dayID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}


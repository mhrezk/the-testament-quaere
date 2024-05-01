import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Year} from "../../../../interfaces/models/calendar/year";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class YearService {
  private baseURL: string = `${environment.API_URL}/calendar`;

  constructor(private http: HttpClient) { }

  getPaginatedYears$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/years?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getYears$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/years/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getYearByID$ = (yearID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/year/${yearID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getYearByID(yearID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/year/${yearID}`);
  }

  saveYear$ = (year: Year) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/year`, year)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateYear$ = (yearID: number, year: Year) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/year/${yearID}`, year)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyYear$ = (yearID: number, year: Year) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${yearID}`, year)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyYear(year: Year): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/year`, year);
  }

  deleteYear$ = (yearID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/year/${yearID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

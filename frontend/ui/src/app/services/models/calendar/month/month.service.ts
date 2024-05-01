import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Month} from "../../../../interfaces/models/calendar/month";

@Injectable({
  providedIn: 'root'
})
export class MonthService {
  private baseURL: string = `${environment.API_URL}/calendar`;

  constructor(private http: HttpClient) { }

  getPaginatedMonths$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/months?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getMonths$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/months/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getMonthByID$ = (monthID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/month/${monthID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getMonthByID(monthID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/month/${monthID}`);
  }

  saveMonth$ = (month: Month) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/month`, month)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateMonth$ = (monthID: number, month: Month) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/month/${monthID}`, month)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyMonth$ = (monthID: number, month: Month) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${monthID}`, month)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyMonth(month: Month): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/month`, month);
  }

  deleteMonth$ = (monthID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/month/${monthID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

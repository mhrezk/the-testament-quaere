import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Capital} from "../../../../interfaces/models/places/capital";

@Injectable({
  providedIn: 'root'
})
export class CapitalService {
  private baseURL: string = `${environment.API_URL}/places`;

  constructor(private http: HttpClient) { }

  getPaginatedCapitals$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/capitals?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getCapitals$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/capitals/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getCapitalByID$ = (capitalID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/capital/${capitalID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getCapitalByID(capitalID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/capital/${capitalID}`);
  }

  saveCapital$ = (capital: Capital) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/capital`, capital)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateCapital$ = (capitalID: number, capital: Capital) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/capital/${capitalID}`, capital)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyCapital$ = (capitalID: number, capital: Capital) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${capitalID}`, capital)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyCapital(capital: Capital): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/capital`, capital);
  }

  deleteCapital$ = (capitalID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/capital/${capitalID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

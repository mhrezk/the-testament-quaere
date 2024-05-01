import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Pundit} from "../../../../interfaces/models/politics/pundit";

@Injectable({
  providedIn: 'root'
})
export class PunditService {
  private baseURL: string = `${environment.API_URL}/politics`;

  constructor(private http: HttpClient) { }

  getPaginatedPundits$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/pundits?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getPundits$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/pundits/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getPunditByID$ = (punditID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/pundit/${punditID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getPunditByID(punditID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/pundit/${punditID}`);
  }

  savePundit$ = (pundit: Pundit) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/pundit`, pundit)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updatePundit$ = (punditID: number, pundit: Pundit) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/pundit/${punditID}`, pundit)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyPundit$ = (punditID: number, pundit: Pundit) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${punditID}`, pundit)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyPundit(pundit: Pundit): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/pundit`, pundit);
  }

  deletePundit$ = (punditID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/pundit/${punditID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

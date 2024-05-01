import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Fae} from "../../../../../interfaces/models/dogma/mythology/fae";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FaeService {
  private baseURL: string = `${environment.API_URL}/dogma`;

  constructor(private http: HttpClient) { }

  getPaginatedFaes$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/faes?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getFaes$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/faes/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getFaeByID$ = (faeID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/fae/${faeID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getFaeByID(faeID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/fae/${faeID}`);
  }

  saveFae$ = (fae: Fae) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/fae`, fae)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateFae$ = (faeID: number, fae: Fae) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/fae/${faeID}`, fae)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyFae$ = (faeID: number, fae: Fae) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${faeID}`, fae)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyFae(fae: Fae): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/fae`, fae);
  }

  deleteFae$ = (faeID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/fae/${faeID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

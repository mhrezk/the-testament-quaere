import { Injectable } from '@angular/core';
import {environment} from "../../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Angel} from "../../../../../interfaces/models/dogma/mythology/angel";

@Injectable({
  providedIn: 'root'
})
export class AngelService {
  private baseURL: string = `${environment.API_URL}/dogma`;

  constructor(private http: HttpClient) { }

  getPaginatedAngels$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/angels?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAngels$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/angels/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAngelByID$ = (angelID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/angel/${angelID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getAngelByID(angelID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/angel/${angelID}`);
  }

  saveAngel$ = (angel: Angel) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/angel`, angel)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateAngel$ = (angelID: number, angel: Angel) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/Angel/${angelID}`, angel)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyAngel$ = (angelID: number, angel: Angel) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${angelID}`, angel)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyAngel(angel: Angel): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/angel`, angel);
  }

  deleteAngel$ = (angelID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/angel/${angelID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

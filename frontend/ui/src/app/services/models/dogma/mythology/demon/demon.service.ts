import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../../environments/environment";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Demon} from "../../../../../interfaces/models/dogma/mythology/demon";

@Injectable({
  providedIn: 'root'
})
export class DemonService {
  private baseURL: string = `${environment.API_URL}/dogma`;

  constructor(private http: HttpClient) { }

  getPaginatedDemons$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/demons?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getDemons$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/demons/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getDemonByID$ = (demonID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/demon/${demonID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getDemonByID(demonID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/demon/${demonID}`);
  }

  saveDemon$ = (demon: Demon) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/demon`, demon)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateDemon$ = (demonID: number, demon: Demon) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/demon/${demonID}`, demon)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyDemon$ = (demonID: number, demon: Demon) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${demonID}`, demon)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyDemon(demon: Demon): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/demon`, demon);
  }

  deleteDemon$ = (demonID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/demon/${demonID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

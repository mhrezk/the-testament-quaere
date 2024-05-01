import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {NationLeader} from "../../../../interfaces/models/politics/nation-leader";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class NationLeaderService {
  private baseURL: string = `${environment.API_URL}/politics`;

  constructor(private http: HttpClient) { }

  getPaginatedNationLeaders$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/nationLeaders?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getNationLeaders$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/nationLeaders/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getNationLeaderByID$ = (nationLeaderID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/nationLeader/${nationLeaderID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getNationLeaderByID(nationLeaderID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/nationLeader/${nationLeaderID}`);
  }

  saveNationLeader$ = (nationLeader: NationLeader) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/NationLeader`, nationLeader)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateNationLeader$ = (nationLeaderID: number, nationLeader: NationLeader) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/nationLeader/${nationLeaderID}`, nationLeader)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyNationLeader$ = (nationLeaderID: number, nationLeader: NationLeader) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${nationLeaderID}`, nationLeader)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyNationLeader(nationLeader: NationLeader): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/nationLeader`, nationLeader);
  }

  deleteNationLeader$ = (nationLeaderID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/nationLeader/${nationLeaderID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

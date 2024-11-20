import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Prophet} from "../../../../interfaces/models/dogma/prophet";

@Injectable({
  providedIn: 'root'
})
export class ProphetService {
  private baseURL: string = `${environment.API_URL}/dogma`;

  constructor(private http: HttpClient) { }

  getPaginatedProphets$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/prophets?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getPaginatedProphetsByReligionName$ = (religionName: string, number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/prophets/religion/${religionName}?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getProphets$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/prophets/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getProphetByID$ = (prophetID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/prophet/${prophetID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getProphetByID(prophetID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/prophet/${prophetID}`);
  }

  getAllProphetsByReligionName$ = (religionName: string) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/prophets/${religionName}/count`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  saveProphet$ = (prophet: Prophet) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/prophet`, prophet)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateProphet$ = (prophetID: number, prophet: Prophet) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/prophet/${prophetID}`, prophet)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyProphet$ = (prophetID: number, prophet: Prophet) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/prophet/${prophetID}`, prophet)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyProphet(prophetID: number, prophet: Prophet): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/prophet/${prophetID}`, prophet);
  }

  deleteProphet$ = (prophetID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/prophet/${prophetID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

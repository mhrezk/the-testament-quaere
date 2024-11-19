import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Religion} from "../../../../interfaces/models/dogma/religion";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ReligionService {
  private baseURL: string = `${environment.API_URL}/dogma`;

  constructor(private http: HttpClient) { }

  getPaginatedReligions$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/religions?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getReligions$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/religions/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getAllReligionsCount(): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/religions/all/count`)
  }

  getReligionByID$ = (religionID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/religion/${religionID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getReligionByID(religionID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/religion/${religionID}`);
  }

  saveReligion$ = (religion: Religion) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/religion`, religion)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateReligion$ = (religionID: number, religion: Religion) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/religion/${religionID}`, religion)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyReligion$ = (religionID: number, religion: Religion) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/religion/${religionID}`, religion)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyReligion(religionID: number, religion: Religion): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/religion/${religionID}`, religion);
  }

  deleteReligion$ = (religionID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/religion/${religionID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  deleteNationFromReligion$ = (religionID: number, nationName: string) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/religion/${religionID}/${nationName}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

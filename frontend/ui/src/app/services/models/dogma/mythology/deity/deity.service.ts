import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Deity} from "../../../../../interfaces/models/dogma/mythology/deity";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class DeityService {
  private baseURL: string = `${environment.API_URL}/dogma`;

  constructor(private http: HttpClient) { }

  getPaginatedDeities$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/deities?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getPaginatedDeitiesByReligionName$ = (religionName: string, number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/deities/religion/${religionName}?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getDeities$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/deities/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAllDeitiesByReligionName$ = (religionName: string) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/deities/${religionName}/count`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getDeityByID$ = (deityID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/deity/${deityID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getDeityByID(deityID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/deity/${deityID}`);
  }

  saveDeity$ = (religionName: string, deity: Deity) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/deity/${religionName}`, deity)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateDeity$ = (deityID: number, deity: Deity) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/deity/${deityID}`, deity)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyDeity$ = (deityID: number, deity: Deity) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/deity/${deityID}`, deity)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyDeity(deity: Deity): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/deity`, deity);
  }

  deleteDeity$ = (deityID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/deity/${deityID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

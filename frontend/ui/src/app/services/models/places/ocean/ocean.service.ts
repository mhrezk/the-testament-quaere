import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Ocean} from "../../../../interfaces/models/places/ocean";

@Injectable({
  providedIn: 'root'
})
export class OceanService {
  private baseURL: string = `${environment.API_URL}/places`;

  constructor(private http: HttpClient) { }

  getPaginatedOceans$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/oceans?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getOceans$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/oceans/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getOceanByID$ = (oceanID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/ocean/${oceanID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getOceanByID(oceanID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/ocean/${oceanID}`);
  }

  saveOcean$ = (ocean: Ocean) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/ocean`, ocean)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateOcean$ = (oceanID: number, ocean: Ocean) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/ocean/${oceanID}`, ocean)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyOcean$ = (oceanID: number, ocean: Ocean) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${oceanID}`, ocean)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyOcean(ocean: Ocean): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/ocean`, ocean);
  }

  deleteOcean$ = (oceanID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/ocean/${oceanID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

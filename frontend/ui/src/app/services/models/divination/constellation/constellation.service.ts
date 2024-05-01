import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Constellation} from "../../../../interfaces/models/divination/constellation";

@Injectable({
  providedIn: 'root'
})
export class ConstellationService {
  private baseURL: string = `${environment.API_URL}/divination`;

  constructor(private http: HttpClient) { }

  getPaginatedConstellations$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/constellations?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getConstellations$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/constellations/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getConstellationByID$ = (constellationID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/constellation/${constellationID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getConstellationByID(constellationID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/constellation/${constellationID}`);
  }

  saveConstellation$ = (constellation: Constellation) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/constellation`, constellation)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateConstellation$ = (constellationID: number, constellation: Constellation) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/constellation/${constellationID}`, constellation)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyConstellation$ = (constellationID: number, constellation: Constellation) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${constellationID}`, constellation)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyConstellation(constellation: Constellation): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/constellation`, constellation);
  }

  deleteConstellation$ = (constellationID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/constellation/${constellationID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

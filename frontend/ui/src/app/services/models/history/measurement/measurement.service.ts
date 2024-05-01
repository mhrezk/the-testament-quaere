import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Measurement} from "../../../../interfaces/models/history/measurement";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MeasurementService {

  private baseURL: string = `${environment.API_URL}/history`;

  constructor(private http: HttpClient) { }

  getPaginatedMeasurements$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/measurements?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getMeasurements$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/measurements/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getMeasurementByID$ = (measurementID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/measurement/${measurementID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getMeasurementByID(measurementID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/measurement/${measurementID}`);
  }

  saveMeasurement$ = (measurement: Measurement) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/measurement`, measurement)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateMeasurement$ = (measurementID: number, measurement: Measurement) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/measurement/${measurementID}`, measurement)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyMeasurement$ = (measurementID: number, measurement: Measurement) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${measurementID}`, measurement)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyMeasurement(measurement: Measurement): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/measurement`, measurement);
  }

  deleteMeasurement$ = (measurementID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/measurement/${measurementID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

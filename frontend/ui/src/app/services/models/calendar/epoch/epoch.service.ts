import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Epoch} from "../../../../interfaces/models/calendar/epoch";

@Injectable({
  providedIn: 'root'
})
export class EpochService {
  private baseURL: string = `${environment.API_URL}/calendar`;

  constructor(private http: HttpClient) { }

  getPaginatedEpochs$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/epochs?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getEpochs$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/epochs/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getEpochByID$ = (epochID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/epoch/${epochID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getEpochByID(epochID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/epoch/${epochID}`);
  }

  saveEpoch$ = (epoch: Epoch) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/epoch`, epoch)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateEpoch$ = (epochID: number, epoch: Epoch) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/epoch/${epochID}`, epoch)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyEpoch$ = (epochID: number, epoch: Epoch) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${epochID}`, epoch)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyEpoch(epoch: Epoch): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/epoch`, epoch);
  }

  deleteEpoch$ = (epochID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/epoch/${epochID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

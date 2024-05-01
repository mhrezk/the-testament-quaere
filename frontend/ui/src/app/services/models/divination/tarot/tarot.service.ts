import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Tarot} from "../../../../interfaces/models/divination/tarot";

@Injectable({
  providedIn: 'root'
})
export class TarotService {
  private baseURL: string = `${environment.API_URL}/divination`;

  constructor(private http: HttpClient) { }

  getPaginatedTarots$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/tarots?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getTarots$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/tarots/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getTarotByID$ = (tarotID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/tarot/${tarotID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getTarotByID(tarotID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/tarot/${tarotID}`);
  }

  saveTarot$ = (tarot: Tarot) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/tarot`, tarot)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateTarot$ = (tarotID: number, tarot: Tarot) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/tarot/${tarotID}`, tarot)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyTarot$ = (tarotID: number, tarot: Tarot) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${tarotID}`, tarot)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyTarot(tarot: Tarot): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/tarot`, tarot);
  }

  deleteTarot$ = (tarotID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/tarot/${tarotID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

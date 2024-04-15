import {Injectable} from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";
import { Race } from '../../../../interfaces/models/history/race';


@Injectable({
  providedIn: 'root'
})
export class RaceService {
  private baseURL: string = `${environment.API_URL}/history`;

  constructor(private http: HttpClient) { }

  getRaces$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/races/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getRaceByID$ = (raceID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/race/${raceID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getRaceByName$ = (raceName: string) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/race?name=${raceName}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getRaceByID(raceID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/race/${raceID}`);
  }

  saveRace$ = (race: Race) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/race`, race)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateRace$ = (raceID: number, race: Race) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/person/${raceID}`, race)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyRace$ = (race: Race) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/race`, race)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyRace(race: Race): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/race`, race);
  }

  deleteRace$ = (raceID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/race/${raceID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}


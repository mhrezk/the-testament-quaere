import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, throwError} from "rxjs";
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

  raceName = new BehaviorSubject<string>("");
  getRaceName$ = this.raceName.asObservable();

  raceID = new BehaviorSubject<number>(0);
  getRaceID$ = this.raceID.asObservable();

  constructor(private http: HttpClient) { }

  getPaginatedRaces$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/races?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getRaces$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/races/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAllRacesCount() {
    return this.http.get<CustomResponse>(`${this.baseURL}/races/all/count`)
  }

  getRaceByID$ = (raceID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/race/${raceID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getRaceBydID(raceID: number) {
    return this.http.get<CustomResponse>(`${this.baseURL}/race/${raceID}`);
  }

  getRaceByName$ = (raceName: string) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/race?name=${raceName}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getRaceByID(raceID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/race/${raceID}`);
  }

  doesRaceNameExist(raceName: string) {
    return this.http.get<CustomResponse>(`${this.baseURL}/race/${raceName}/exist`);
  }

  saveRace$ = (race: Race) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/race`, race)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateRace$ = (raceID: number, race: Race) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/race/${raceID}`, race)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateRace(raceID: number, race: Race) {
    return this.http.patch<CustomResponse>(`${this.baseURL}/update/race/${raceID}`, race);
  }

  modifyRace$ = (raceID: number, race: Race) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/race/${raceID}`, race)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyRace(raceID: number, race: Race): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/race/${raceID}`, race);
  }

  deleteRace$ = (raceID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/race/${raceID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  deleteRace(raceID: number) {
    return this.http.delete<CustomResponse>(`${this.baseURL}/delete/race/${raceID}`);
  }

  setRaceDetails(raceID: number, raceName: string) {
    this.raceID.next(raceID);
    this.raceName.next(raceName);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}


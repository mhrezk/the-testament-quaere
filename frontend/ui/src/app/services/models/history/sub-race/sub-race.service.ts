import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {SubRace} from "../../../../interfaces/models/history/sub-race";

@Injectable({
  providedIn: 'root'
})
export class SubRaceService {
  private baseURL: string = `${environment.API_URL}/history`;

  constructor(private http: HttpClient) { }

  getPaginatedSubRaces$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/subRaces?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getPaginatedSubRacesByRaceName$ = (raceName: string, number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/subRaces/${raceName}/all?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getSubRacesByRaceName$ = (name: string) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/subRaces/race?raceName=${name}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getSubRaces$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/subRaces/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAllSubRacesCount() {
    return this.http.get<CustomResponse>(`${this.baseURL}/subRaces/all/count`)
  }

  getSubRacesCountByRaceName(name: string) {
    return this.http.get<CustomResponse>(`${this.baseURL}/subRaces/${name}/count`)
  }

  getSubRaceByID$ = (subRaceID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/subRace/${subRaceID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getSubRaceByName$ = (subRaceName: string) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/subRace?name=${subRaceName}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getSubRaceByID(subRaceID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/subRace/${subRaceID}`);
  }

  doesSubRaceNameExist(subRaceName: string) {
    return this.http.get<CustomResponse>(`${this.baseURL}/subRace/${subRaceName}/exist`);
  }

  saveSubRace$ = (subRace: SubRace, raceName: string) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/subRace/${raceName}`, subRace)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateSubRace$ = (subRaceID: number, raceName: string, subRace: SubRace) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/subRace/${subRaceID}/${raceName}`, subRace)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateSubRace(subRaceID: number, raceName: string, subRace: SubRace) {
    return this.http.patch<CustomResponse>(`${this.baseURL}/update/subRace/${subRaceID}/${raceName}`, subRace);
  }

  modifySubRace$ = (subRaceID: number, raceName: string, subRace: SubRace) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/subRace/${subRaceID}/${raceName}`, subRace)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifySubRace(subRaceID: number, raceName: string, subRace: SubRace): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/subRace/${subRaceID}/${raceName}`, subRace);
  }

  deleteSubRace$ = (subRaceID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/subRace/${subRaceID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  deleteSubRace(subRaceID: number) {
    return this.http.delete<CustomResponse>(`${this.baseURL}/delete/subRace/${subRaceID}`);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

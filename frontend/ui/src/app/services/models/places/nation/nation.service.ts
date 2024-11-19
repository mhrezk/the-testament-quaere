import {Injectable} from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";
import {Nation} from "../../../../interfaces/models/places/nation";

@Injectable({
  providedIn: 'root'
})
export class NationService {

  private baseURL: string = `${environment.API_URL}/places`;

  constructor(private http: HttpClient) { }

  getPaginatedNations$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/nations?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAllNationsCount() {
    return this.http.get<CustomResponse>(`${this.baseURL}/nations/all/count`);
  }

  getNationByID$ = (nationID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/nation/${nationID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getNationByID(nationID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/nation/${nationID}`);
  }

  saveNation$ = (nation: Nation) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/nation`, nation)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateNation$ = (nationID: number, nation: Nation) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/nation/${nationID}`, nation)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyNation$ = (nationID: number, nation: Nation) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/nation/${nationID}`, nation)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyNation(nationID: number, nation: Nation): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/nation?${nationID}`, nation);
  }

  deleteNation$ = (nationID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/nation/${nationID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  deleteNationByName$ = (nationName: string) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/nation?nationName=${nationName}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  deleteNation(nationID: number) {
    return this.http.delete<CustomResponse>(`${this.baseURL}/delete/nation/${nationID}`);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

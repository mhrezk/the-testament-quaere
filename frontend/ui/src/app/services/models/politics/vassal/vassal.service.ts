import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Vassal} from "../../../../interfaces/models/politics/vassal";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class VassalService {
  private baseURL: string = `${environment.API_URL}/politics`;

  constructor(private http: HttpClient) { }

  getPaginatedVassals$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/vassals?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getVassals$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/vassals/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getVassalByID$ = (vassalID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/vassal/${vassalID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getVassalByID(vassalID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/vassal/${vassalID}`);
  }

  saveVassal$ = (vassal: Vassal) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/vassal`, vassal)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateVassal$ = (vassalID: number, vassal: Vassal) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/vassal/${vassalID}`, vassal)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyVassal$ = (vassalID: number, vassal: Vassal) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${vassalID}`, vassal)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyVassal(vassal: Vassal): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/vassal`, vassal);
  }

  deleteVassal$ = (vassalID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/vassal/${vassalID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

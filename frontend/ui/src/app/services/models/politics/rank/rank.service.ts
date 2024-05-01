import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Rank} from "../../../../interfaces/models/politics/rank";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class RankService {
  private baseURL: string = `${environment.API_URL}/politics`;

  constructor(private http: HttpClient) { }

  getPaginatedRanks$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/ranks?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getRanks$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/ranks/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getRankByID$ = (rankID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/rank/${rankID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getRankByID(rankID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/rank/${rankID}`);
  }

  saveRank$ = (rank: Rank) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/rank`, rank)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateRank$ = (rankID: number, rank: Rank) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/rank/${rankID}`, rank)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyRank$ = (rankID: number, rank: Rank) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${rankID}`, rank)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyRank(rank: Rank): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/rank`, rank);
  }

  deleteRank$ = (rankID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/rank/${rankID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

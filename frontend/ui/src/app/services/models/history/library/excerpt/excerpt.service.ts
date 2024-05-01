import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Excerpt} from "../../../../../interfaces/models/history/library/excerpt";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ExcerptService {
  private baseURL: string = `${environment.API_URL}/history`;

  constructor(private http: HttpClient) { }

  getPaginatedExcerpts$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/excerpts?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getExcerpts$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/excerpts/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getExcerptByID$ = (excerptID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/excerpt/${excerptID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getExcerptByID(excerptID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/excerpt/${excerptID}`);
  }

  saveExcerpt$ = (excerpt: Excerpt) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/excerpt`, excerpt)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateExcerpt$ = (excerptID: number, excerpt: Excerpt) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/excerpt/${excerptID}`, excerpt)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyExcerpt$ = (excerptID: number, excerpt: Excerpt) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${excerptID}`, excerpt)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyExcerpt(excerpt: Excerpt): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/excerpt`, excerpt);
  }

  deleteExcerpt$ = (excerptID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/excerpt/${excerptID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

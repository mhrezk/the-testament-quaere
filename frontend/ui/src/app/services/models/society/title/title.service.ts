import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";
import {Title} from "../../../../interfaces/models/society/title";

@Injectable({
  providedIn: 'root'
})
export class TitleService {
  private baseURL: string = `${environment.API_URL}/society`;

  constructor(private http: HttpClient) { }

  getPaginatedTitles$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/titles?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getTitleByID$ = (titleID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/title/${titleID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getTitleByID(titleID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/title/${titleID}`);
  }

  saveTitle$ = (title: Title) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/title`, title)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateTitle$ = (titleID: number, title: Title) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/title/${titleID}`, title)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyTitle$ = (titleID: number, title: Title) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${titleID}`, title)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyTitle(title: Title): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/title`, title);
  }

  deleteTitle$ = (titleID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/title/${titleID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

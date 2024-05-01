import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Language} from "../../../../interfaces/models/history/language";

@Injectable({
  providedIn: 'root'
})
export class LanguageService {
  private baseURL: string = `${environment.API_URL}/history`;

  constructor(private http: HttpClient) { }

  getPaginatedLanguages$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/languages?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getLanguages$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/languages/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getLanguageByID$ = (languageID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/language/${languageID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getLanguageByID(languageID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/language/${languageID}`);
  }

  saveLanguage$ = (language: Language) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/language`, language)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateLanguage$ = (languageID: number, language: Language) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/language/${languageID}`, language)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyLanguage$ = (languageID: number, language: Language) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${languageID}`, language)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyLanguage(language: Language): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/language`, language);
  }

  deleteLanguage$ = (languageID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/language/${languageID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

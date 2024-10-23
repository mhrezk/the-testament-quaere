import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {BehaviorSubject, Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Language} from "../../../../interfaces/models/history/language";

@Injectable({
  providedIn: 'root'
})
export class LanguageService {
  private baseURL: string = `${environment.API_URL}/history`;

  languageName = new BehaviorSubject<string>("");
  getLanguageName$ = this.languageName.asObservable();

  languageID = new BehaviorSubject<number>(0);
  getLanguageID$ = this.languageID.asObservable();

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

  getAllLanguagesCount() {
    return this.http.get<CustomResponse>(`${this.baseURL}/languages/all/count`);
  }

  doesLanguageNameExist(languageName: string) {
    return this.http.get<CustomResponse>(`${this.baseURL}/language/${languageName}/exist`);
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

  updateLanguage(languageID: number, language: Language) {
    return this.http.patch<CustomResponse>(`${this.baseURL}/update/language/${languageID}`, language);
  }

  modifyLanguage$ = (languageID: number, language: Language) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/language/${languageID}`, language)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyLanguage(languageID: number, language: Language): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/language/${languageID}`, language);
  }

  deleteLanguage$ = (languageID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/language/${languageID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  deleteLanguage(languageID: number) {
    return this.http.delete<CustomResponse>(`${this.baseURL}/delete/language/${languageID}`)
  }

  setLanguageDetails(languageID: number, languageName: string) {
    this.languageName.next(languageName);
    this.languageID.next(languageID);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

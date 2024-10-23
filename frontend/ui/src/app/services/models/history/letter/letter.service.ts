import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Letter} from "../../../../interfaces/models/history/letter";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LetterService {
  private baseURL: string = `${environment.API_URL}/history`;

  constructor(private http: HttpClient) { }

  getPaginatedLetters$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/letters?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getLettersByLanguageName$ = (languageName: string) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/letters/${languageName}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getLetters$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/letters/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getLetterByID$ = (letterID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/letter/${letterID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getLetterByID(letterID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/letter/${letterID}`);
  }

  public getLetterByName(letterName: string): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/letter/${letterName}`);
  }

  saveLetter$ = (letter: Letter, languageName: string) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/letter/${languageName}`, letter)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateLetter$ = (letterID: number, letter: Letter) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/letter/${letterID}`, letter)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateLetter(letterID: number, letter: Letter) {
    return this.http.patch<CustomResponse>(`${this.baseURL}/update/letter/${letterID}`, letter)
  }

  modifyLetter$ = (letterID: number, letter: Letter) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/letter/${letterID}`, letter)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyLetter(letter: Letter): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/letter`, letter);
  }

  deleteLetter$ = (letterID: number, languageName: string) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/letter/${letterID}/${languageName}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  deleteLetter(letterID: number, languageName: string) {
    return this.http.delete<CustomResponse>(`${this.baseURL}/delete/letter/${letterID}/${languageName}`);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

import { Injectable } from '@angular/core';
import {environment} from "../../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Author} from "../../../../../interfaces/models/history/library/author";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  private baseURL: string = `${environment.API_URL}/history`;

  constructor(private http: HttpClient) { }

  getPaginatedAuthors$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/authors?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAuthors$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/authors/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAuthorByID$ = (authorID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/author/${authorID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getAuthorByID(authorID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/author/${authorID}`);
  }

  saveAuthor$ = (author: Author) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/author`, author)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateAuthor$ = (authorID: number, author: Author) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/author/${authorID}`, author)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyAuthor$ = (authorID: number, author: Author) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${authorID}`, author)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyAuthor(author: Author): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/author`, author);
  }

  deleteAuthor$ = (authorID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/author/${authorID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

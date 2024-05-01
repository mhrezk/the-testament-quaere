import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Book} from "../../../../../interfaces/models/history/library/book";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseURL: string = `${environment.API_URL}/history`;

  constructor(private http: HttpClient) { }

  getPaginatedBooks$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/books?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getBooks$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/books/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getBookByID$ = (bookID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/book/${bookID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getBookByID(bookID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/book/${bookID}`);
  }

  saveBook$ = (book: Book) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/book`, book)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateBook$ = (bookID: number, book: Book) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/book/${bookID}`, book)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyBook$ = (bookID: number, book: Book) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${bookID}`, book)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyBook(book: Book): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/book`, book);
  }

  deleteBook$ = (bookID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/book/${bookID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Book} from "../../../../../interfaces/models/history/library/book";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseURL: string = `${environment.API_URL}/history/library`;

  bookCountSubject = new BehaviorSubject<number>(0);
  bookCount$ = this.bookCountSubject.asObservable();

  constructor(private http: HttpClient) { }

  getPaginatedBooks$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/books?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getPaginatedBooksByAuthorName$ = (id: number, firstName: string, lastName: string, number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/books/${id}/${firstName}/${lastName}?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getBooks$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/books/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAllBooksCount() {
    return this.http.get<CustomResponse>(`${this.baseURL}/books/all/count`)
  }

  getAllBooksByAuthorNameCount(authorID: number, firstName: string, lastName: string) {
    return this.http.get<CustomResponse>(`${this.baseURL}/books/${authorID}/${firstName}/${lastName}/count`)
  }

  getBookByID$ = (bookID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/book/${bookID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getBookByID(bookID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/book/${bookID}`);
  }

  saveBook$ = (authorID: number, firstName: string, lastName: string, book: Book) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/book/${authorID}/${firstName}/${lastName}`, book)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateBook$ = (bookID: number, authorID: number, book: Book) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/book/${bookID}/${authorID}`, book)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyBook$ = (bookID: number, authorID: number, book: Book) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/book/${bookID}/${authorID}`, book)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyBook(bookID: number, authorID: number, book: Book): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/book/${bookID}/${authorID}`, book);
  }

  deleteBook$ = (bookID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/book/${bookID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  setBookCount(count: number) {
    this.bookCountSubject.next(count);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

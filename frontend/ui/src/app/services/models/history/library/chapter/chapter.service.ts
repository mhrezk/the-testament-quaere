import { Injectable } from '@angular/core';
import {environment} from "../../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Chapter} from "../../../../../interfaces/models/history/library/chapter";

@Injectable({
  providedIn: 'root'
})
export class ChapterService {
  private baseURL: string = `${environment.API_URL}/history/library`;

  constructor(private http: HttpClient) { }

  getPaginatedChapters$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/chapters?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getPaginatedChaptersByBookName$ = (bookName: string, number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/chapters/${bookName}?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getChaptersByBookName$ = (bookName: string) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/chapters/${bookName}/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getChapters$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/chapters/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAllChaptersByBookNameCount(bookName: string) {
    return this.http.get<CustomResponse>(`${this.baseURL}/chapters/${bookName}/count`);
  }

  getChapterByID$ = (chapterID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/chapter/${chapterID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getChapterByID(chapterID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/chapter/${chapterID}`);
  }

  saveChapter$ = (chapter: Chapter, bookName: string) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/chapter/${bookName}`, chapter)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateChapter$ = (chapterID: number, chapter: Chapter) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/chapter/${chapterID}`, chapter)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyChapter$ = (chapterID: number, chapter: Chapter) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/chapter/${chapterID}`, chapter)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyChapter(chapterID: number, chapter: Chapter): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/chapter/${chapterID}`, chapter);
  }

  deleteChapter$ = (chapterID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/chapter/${chapterID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

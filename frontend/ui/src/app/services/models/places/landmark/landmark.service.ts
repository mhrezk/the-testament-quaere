import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Landmark} from "../../../../interfaces/models/places/landmark";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LandmarkService {
  private baseURL: string = `${environment.API_URL}/places`;

  constructor(private http: HttpClient) { }

  getPaginatedLandmarks$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/landmarks?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getLandmarks$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/landmarks/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getLandmarkByID$ = (landmarkID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/landmark/${landmarkID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getLandmarkByID(landmarkID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/landmark/${landmarkID}`);
  }

  saveLandmark$ = (landmark: Landmark) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/landmark`, landmark)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateLandmark$ = (landmarkID: number, landmark: Landmark) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/landmark/${landmarkID}`, landmark)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyLandmark$ = (landmarkID: number, landmark: Landmark) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${landmarkID}`, landmark)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyLandmark(landmark: Landmark): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/landmark`, landmark);
  }

  deleteLandmark$ = (landmarkID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/landmark/${landmarkID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

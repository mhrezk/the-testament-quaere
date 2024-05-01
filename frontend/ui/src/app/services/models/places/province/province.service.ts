import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Province} from "../../../../interfaces/models/places/province";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProvinceService {
  private baseURL: string = `${environment.API_URL}/places`;

  constructor(private http: HttpClient) { }

  getPaginatedProvinces$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/provinces?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getProvinces$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/provinces/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getProvinceByID$ = (provinceID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/province/${provinceID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getProvinceByID(provinceID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/province/${provinceID}`);
  }

  saveProvince$ = (province: Province) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/province`, province)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateProvince$ = (provinceID: number, province: Province) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/province/${provinceID}`, province)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyProvince$ = (provinceID: number, province: Province) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${provinceID}`, province)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyProvince(province: Province): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/province`, province);
  }

  deleteProvince$ = (provinceID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/province/${provinceID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

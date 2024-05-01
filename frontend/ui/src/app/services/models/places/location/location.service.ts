import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Location} from "../../../../interfaces/models/places/location";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  private baseURL: string = `${environment.API_URL}/places`;

  constructor(private http: HttpClient) { }

  getPaginatedLocations$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/locations?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getLocations$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/locations/all`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getLocationByID$ = (locationID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/location/${locationID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getLocationByID(locationID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/location/${locationID}`);
  }

  saveLocation$ = (location: Location) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/location`, location)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateLocation$ = (locationID: number, location: Location) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/location/${locationID}`, location)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyLocation$ = (locationID: number, location: Location) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${locationID}`, location)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyLocation(location: Location): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/location`, location);
  }

  deleteLocation$ = (locationID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/location/${locationID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

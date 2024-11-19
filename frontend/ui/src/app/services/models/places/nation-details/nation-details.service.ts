import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {NationDetails} from "../../../../interfaces/models/places/nation-details";

@Injectable({
  providedIn: 'root'
})
export class NationDetailsService {
  private baseURL: string = `${environment.API_URL}/places`

  constructor(private http: HttpClient) {}

  public getNationDetailsByNationName(nationName: string): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/nationDetails/${nationName}`);
  }

  deleteNationDetails$ = (nationDetailsID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/nationDetails/${nationDetailsID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public deleteNationDetailsByID(id: number): Observable<CustomResponse> {
    return this.http.delete<CustomResponse>(`${this.baseURL}/delete/nationDetails/${id}`);
  }

  modifyNationDetails$ = (nationDetailsID: number, nationDetails: NationDetails) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/nationDetails/${nationDetailsID}`, nationDetails)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateNationDetails(nationDetailsID: number, nationDetails: NationDetails) {
    return this.http.patch<CustomResponse>(`${this.baseURL}/update/nationDetails/${nationDetailsID}`, nationDetails)
  }

  public modifyNationDetails(id: number, nationDetails: NationDetails): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/nationDetails/${id}`, nationDetails);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

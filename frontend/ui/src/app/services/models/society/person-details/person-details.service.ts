import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {PersonDetails} from "../../../../interfaces/models/society/person-details";

@Injectable({
  providedIn: 'root'
})
export class PersonDetailsService {
  private baseURL: string = `${environment.API_URL}/society`

  constructor(private http: HttpClient) { }

  public getPersonDetailsByFirstNameAndSecondName(id: number, firstName: string, secondName: string): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/personDetails/${id}/${firstName}/${secondName}`);
  }

  deletePerson$ = (personDetailsID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/personDetails/${personDetailsID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public deletePersonDetailsByID(id: number): Observable<CustomResponse> {
    return this.http.delete<CustomResponse>(`${this.baseURL}/delete/personDetails/${id}`);
  }

  modifyPersonDetails$ = (personDetailsID: number, personDetails: PersonDetails) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/personDetails/${personDetailsID}`, personDetails)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updatePersonDetails(personDetailsID: number, personDetails: PersonDetails) {
    return this.http.patch<CustomResponse>(`${this.baseURL}/update/personDetails/${personDetailsID}`, personDetails)
  }

  public modifyPersonDetails(id: number, personDetails: PersonDetails): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/personDetails/${id}`, personDetails);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

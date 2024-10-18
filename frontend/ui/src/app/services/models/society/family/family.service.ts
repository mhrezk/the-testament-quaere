import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {Observable, throwError} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {Family} from "../../../../interfaces/models/society/family";

@Injectable({
  providedIn: 'root'
})

export class FamilyService {
  private baseURL: string = `${environment.API_URL}/society`;

  constructor(private http: HttpClient) { }

  getFamiliesByCommunityName$ = (communityName: string) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/families/${communityName}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getFamiliesByCommunityName(communityName: string): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/families/${communityName}`)
  }

  getFamiliesCountByCommunityName(communityName: string): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/families/${communityName}/count`)
  }

  public getFamilyByID(communityID: string): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/family/${communityID}`);
  }

  saveFamily$ = (family: Family) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/family`, family)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  saveFamily(family: Family) {
    return this.http.post<CustomResponse>(`${this.baseURL}/save/family`, family);
  }

  saveFamilies(families: Family[], communityID: number, communitySize: number) {
    return this.http.post<CustomResponse>(`${this.baseURL}/save/families/${communityID}?communitySize=${communitySize}`, families);
  }

  updateFamily(familyID: number, family: Family) {
    return this.http.patch<CustomResponse>(`${this.baseURL}/update/family/${familyID}`, family)
  }

  modifyFamily$ = (familyID: number, family: Family) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/family/${familyID}`, family)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyFamily(familyID: number, family: Family) {
    this.http.put<CustomResponse>(`${this.baseURL}/modify/family/${familyID}`, family);
  }

  deleteFamily$ = (familyID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/family/${familyID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public deleteFamily(familyID: number): Observable<CustomResponse> {
    return this.http.delete<CustomResponse>(`${this.baseURL}/delete/family/${familyID}`);
  }

  public deleteAllFamilies(): Observable<CustomResponse> {
    return this.http.delete<CustomResponse>(`${this.baseURL}/delete/families`);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {BehaviorSubject, Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Community} from "../../../../interfaces/models/society/family-node/community";

@Injectable({
  providedIn: 'root'
})
export class CommunityService {
  private baseURL: string = `${environment.API_URL}/society`;

  private communityID = new BehaviorSubject<number>(0);
  getCommunityID$ = this.communityID.asObservable();

  private name = new BehaviorSubject<string>("");
  getName$ = this.name.asObservable();

  constructor(private http: HttpClient) { }

  getPaginatedCommunities$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/communities?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAllCommunitiesCount() {
    return this.http.get<CustomResponse>(`${this.baseURL}/communities/all/count`)
  }

  public getCommunityByID(communityID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/community/${communityID}`);
  }

  saveCommunity$ = (community: Community) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/community`, community)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateCommunity(communityID: number, community: Community) {
    return this.http.patch<CustomResponse>(`${this.baseURL}/update/community/${communityID}`, community)
  }

  modifyCommunity$ = (communityID: number, community: Community) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/${communityID}`, community)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  deleteCommunity$ = (communityID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/community/${communityID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public deleteCommunity(communityID: number): Observable<CustomResponse> {
    return this.http.delete<CustomResponse>(`${this.baseURL}/delete/community/${communityID}`);
  }

  setCommunityName(name: string) {
    this.name.next(name);
  }

  setCommunityID(communityID: number) {
    this.communityID.next(communityID);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

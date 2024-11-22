import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";
import {Organization} from "../../../../interfaces/models/politics/organization";

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {
  private organizationUpdate = new BehaviorSubject<Organization>(null);
  organizationUpdated$ = this.organizationUpdate.asObservable();

  private baseURL: string = `${environment.API_URL}/politics`;

  constructor(private http: HttpClient) { }

  getPaginatedOrganizations$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/organizations?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getOrganizationByID$ = (organizationID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/organization/${organizationID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getAllOrganizationsCount$ = () => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/organizations/all/count`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getOrganizationByID(organizationID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/organization/${organizationID}`);
  }

  saveOrganization$ = (organization: Organization) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/organization`, organization)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateOrganization$ = (organizationID: number, organization: Organization) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/organization/${organizationID}`, organization)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyOrganization$ = (organizationID: number, organization: Organization) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/modify/organization/${organizationID}`, organization)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyOrganization(organization: Organization): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/organization`, organization);
  }

  deleteOrganization$ = (organizationID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/organization/${organizationID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  notifyUpdate(organization: Organization) {
    this.organizationUpdate.next(organization);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

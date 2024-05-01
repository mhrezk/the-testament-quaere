import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {catchError, tap} from "rxjs/operators";
import {Job} from "../../../../interfaces/models/society/job";

@Injectable({
  providedIn: 'root'
})
export class JobService {
  private baseURL: string = `${environment.API_URL}/society`;

  constructor(private http: HttpClient) { }

  getPaginatedJobs$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/jobs?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getJobByID$ = (jobID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/job/${jobID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getJobByID(jobID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/job/${jobID}`);
  }

  saveJob$ = (job: Job) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/job`, job)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updateJob$ = (jobID: number, job: Job) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/job/${jobID}`, job)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyJob$ = (jobID: number, job: Job) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${jobID}`, job)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyJob(job: Job): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/update/job`, job);
  }

  deleteJob$ = (jobID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/job/${jobID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

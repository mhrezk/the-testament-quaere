import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {Gender} from "../../../../enums/gender";
import {catchError, tap} from "rxjs/operators";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";
import {Person} from "../../../../interfaces/models/society/person";

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private baseURL: string = `${environment.API_URL}/society`;

  private personID = new BehaviorSubject<number>(0);
  getPersonID$ = this.personID.asObservable();

  private firstName = new BehaviorSubject<string>("");
  getFirstName$ = this.firstName.asObservable();

  private secondName = new BehaviorSubject<string>("");
  getSecondName$ = this.secondName.asObservable();

  constructor(private http: HttpClient) { }

  getPaginatedPeople$ = (number: number, size: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/persons?pageNumber=${number}&&pageSize=${size}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  getPersonByID$ = (personID: number) => <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.baseURL}/person/${personID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public getPersonByID(personID: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${this.baseURL}/person/${personID}`);
  }

  savePerson$ = (person: Person) => <Observable<CustomResponse>>this.http.post<CustomResponse>(`${this.baseURL}/save/person`, person)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  updatePerson$ = (personID: number, person: Person) => <Observable<CustomResponse>>this.http.patch<CustomResponse>(`${this.baseURL}/update/person/${personID}`, person)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  modifyPerson$ = (personID: number, person: Person) => <Observable<CustomResponse>>this.http.put<CustomResponse>(`${this.baseURL}/update/${personID}`, person)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  public modifyPerson(personID: number, person: Person): Observable<CustomResponse> {
    return this.http.put<CustomResponse>(`${this.baseURL}/modify/person/${personID}`, person);
  }

  deletePerson$ = (personID: number) => <Observable<CustomResponse>>this.http.delete<CustomResponse>(`${this.baseURL}/delete/person/${personID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  filterByGender$ = (gender: Gender, response: CustomResponse) =>
    <Observable<CustomResponse>> new Observable<CustomResponse>(
      subscriber => {
        console.log(response);
        subscriber.next(
          gender === Gender.ALL ?
            {
              ...response,
              message: `People gender have been filtered by: ${gender}`
            } :
            {
              ...response,
              message: response.data.dataRetrieved?.filter(person => person.gender === gender).length > 0 ?
                `People have been filtered by gender type: ${gender === Gender.MALE ? 'Male' : 'Female'}`:
                `No people found of gender type: ${gender}`,
              data: {
                dataRetrieved: response.data.dataRetrieved?.filter(person => person.gender === gender)
              }
            }
        );
        subscriber.complete(); //emits the data contained within subscriber.next()
      }
    ).pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  setFullName(firstName: string, secondName: string) {
    this.firstName.next(firstName);
    this.secondName.next(secondName);
  }

  setPersonID(personID: number) {
    this.personID.next(personID);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}
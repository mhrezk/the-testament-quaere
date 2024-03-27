import {Injectable} from '@angular/core';
import {Observable, throwError} from "rxjs";
import {CustomResponse} from "../../../interfaces/custom-response/custom-response";
import {Gender} from "../../../enums/gender/gender";
import {catchError, tap} from "rxjs/operators";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private http: HttpClient) { }

  filterByGender$ = (gender: Gender, response: CustomResponse) =>
    <Observable<CustomResponse>> new Observable<CustomResponse>(
      subscriber => {
        console.log(response);
        subscriber.next(
          gender === Gender.ALL ?
            {
              ...response,
              message: `People have been filtered by gender type: ${gender}`
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
        subscriber.complete(); //emits the data contained within next()
      }
    ).pipe(
      tap(console.log),
      catchError(this.handleError)
    );

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.message}`);
  }
}

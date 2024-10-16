import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, map, Observable, of, startWith, throwError} from "rxjs";
import {AppState} from "../../../../interfaces/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {DataState} from "../../../../enums/data-state";
import {PersonDetails} from "../../../../interfaces/models/society/person-details";
import {PersonDetailsService} from "../../../../services/models/society/person-details/person-details.service";
import {PersonService} from "../../../../services/models/society/person/person.service";
import {
  faTrash,
  faEdit,
} from '@fortawesome/free-solid-svg-icons';
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {Gender} from "../../../../enums/gender";
import {Family} from "../../../../interfaces/models/society/family";

@Component({
  selector: 'app-person-details',
  templateUrl: './person-details.component.html',
  styleUrl: './person-details.component.css'
})
export class PersonDetailsComponent implements OnInit {
  isEditing: boolean = false;
  isBiography: boolean = false;
  isFamilyTree: boolean = false;

  faTrash = faTrash;
  faEdit = faEdit;

  firstName: string;
  secondName: string;
  personID: number;

  peronDetailsFamilyMap: Family;
  family: Family;
  familyNodeSubject = new BehaviorSubject<Family>(null)
  familyNodeSubject$ = this.familyNodeSubject.asObservable();

  selectedPersonDetails: PersonDetails;
  personDetails = new BehaviorSubject<CustomResponse>(null);

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  constructor(private personDetailsService: PersonDetailsService,
              private personService: PersonService,
              private router: Router) {
  }

  ngOnInit() {
    this.personService.getFirstName$.subscribe(result => this.firstName = result);
    this.personService.getSecondName$.subscribe(result => this.secondName = result);
    this.personService.getPersonID$.subscribe(result => this.personID = result);
    this.getPersonDetailsByFirstNameAndLastName(this.personID, this.firstName, this.secondName);
  }

  getPersonDetailsByFirstNameAndLastName(id: number, firstName: string, secondName: string) {
    // const result = await this.personDetailsService.getPersonDetailsByFirstNameAndSecondName(id, firstName, secondName).toPromise();
    // this.personDetails.next(result);
    // this.peronDetailsFamilyMap = {
    //       firstName: result.data.datumRetrieved.firstName,
    //       secondName: result.data.datumRetrieved.secondName,
    //       gender: result.data.datumRetrieved.gender,
    //     };
    // this.familyNodeSubject.next(this.peronDetailsFamilyMap);
    this.personDetailsService.getPersonDetailsByFirstNameAndSecondName(id, firstName, secondName).subscribe(result => {
      this.selectedPersonDetails = result.data.datumRetrieved;
      this.personDetails.next(result);
      console.log(this.selectedPersonDetails);
      // this.peronDetailsFamilyMap = {
      //   firstName: result.data.datumRetrieved.firstName,
      //   secondName: result.data.datumRetrieved.secondName,
      //   gender: result.data.datumRetrieved.gender,
      // };

      this.familyNodeSubject.next(this.peronDetailsFamilyMap);
      console.log(this.familyNodeSubject.value);
    });
  }

  deletePersonDetails(personDetailsID: number) {
    this.personDetailsService.deletePersonDetailsByID(personDetailsID).subscribe((result) => {
      console.log(result);
    });
    this.router.navigateByUrl("/people");
  }

  closeEditing(editable: boolean) {
    this.isEditing = editable;
  }

  closeBiographyEditing(editable: boolean) {
    this.isBiography = editable;
  }

  onFinishedEditing(personDetails: PersonDetails) {
    this.personDetails.value.data.datumRetrieved = personDetails;
  }

  // onSubmit(form: any) {
  //   console.log('Form Submitted!', form.value);
  // }

  routeToFamilyTree(firstName: string, secondName: string) {
    this.router.navigateByUrl(`/family-tree/${firstName}/${secondName}`)
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`Error: ${error.error.issue}`);
  }
}

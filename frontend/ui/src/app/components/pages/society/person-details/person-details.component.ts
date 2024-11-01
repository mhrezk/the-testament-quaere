import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {PersonDetails} from "../../../../interfaces/models/society/person-details";
import {PersonDetailsService} from "../../../../services/models/society/person-details/person-details.service";
import {PersonService} from "../../../../services/models/society/person/person.service";
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {Router} from "@angular/router";

@Component({
  selector: 'app-person-details',
  templateUrl: './person-details.component.html',
  styleUrl: './person-details.component.css'
})
export class PersonDetailsComponent implements OnInit {
  isEditing: boolean = false;
  isBiography: boolean = false;

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  firstName: string;
  secondName: string;
  personID: number;

  selectedPersonDetails: PersonDetails;
  personDetails = new BehaviorSubject<CustomResponse>(null);

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
    this.personDetailsService.getPersonDetailsByFirstNameAndSecondName(id, firstName, secondName).subscribe(result => {
      this.selectedPersonDetails = result.data.datumRetrieved;
      this.personDetails.next(result);
      console.log(this.selectedPersonDetails);
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

  routeToPeople() {
    this.router.navigate(['/people']);
  }
}

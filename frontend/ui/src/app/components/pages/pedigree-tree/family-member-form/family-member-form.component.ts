import {Component, EventEmitter, Output} from '@angular/core';
import {FamilyMember} from "../../../../interfaces/models/society/family-member";

@Component({
  selector: 'app-family-member-form',
  templateUrl: './family-member-form.component.html',
  styleUrl: './family-member-form.component.css'
})
export class FamilyMemberFormComponent {
  @Output() memberAdded = new EventEmitter<FamilyMember>();

  member: FamilyMember = {
    id: '',
    firstName: '',
    lastName: '',
    gender: 'male',
    location: '',
    birthYear: 0,
    spouses: [],
    children: []
  };

  onSubmit() {
    this.member.id = Date.now().toString();
    this.memberAdded.emit(this.member);
    this.member = {
      id: '',
      firstName: '',
      lastName: '',
      gender: 'male',
      location: '',
      birthYear: 0,
      spouses: [],
      children: []
    };
  }
}

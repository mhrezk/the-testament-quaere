import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FamilyMember} from "../../../../../interfaces/models/society/family-member";

@Component({
  selector: 'app-family-member-node',
  templateUrl: './family-member-node.component.html',
  styleUrl: './family-member-node.component.css'
})
export class FamilyMemberNodeComponent {
  // @Input() member!: FamilyMember;
  // @Output() edit = new EventEmitter<FamilyMember>();
  // @Output() delete = new EventEmitter<FamilyMember>();
  //
  // isEditing = false; // Flag to show/hide the edit form
  // memberData: FamilyMember = { ...this.member }; // Copy the member data for editing
  //
  // ngOnInit() {
  //   // Initialize member data
  //   this.memberData = { ...this.member };
  // }
  //
  // // Function to handle editing
  // editMember() {
  //   this.edit.emit(this.memberData); // Emit the updated member information to the parent component
  //   this.isEditing = false; // Hide the edit form after submitting
  // }
  //
  // // Function to delete this member
  // deleteMember() {
  //   this.delete.emit(this.member); // Emit the current member to the parent component for deletion
  // }

  // getNodeColor(gender: string): string {
  //   return gender === 'male' ? 'lightblue' : 'lightpink';
  // }

  // @Input() member!: FamilyMember;
  // @Output() addParent = new EventEmitter<FamilyMember>();
  // @Output() addSpouse = new EventEmitter<FamilyMember>();
  // @Output() addChild = new EventEmitter<FamilyMember>();
  // @Output() editMember = new EventEmitter<FamilyMember>();
  // @Output() deleteMember = new EventEmitter<FamilyMember>();
  //
  // getGenderColor(): string {
  //   return this.member.gender === 'male' ? '#add8e6' : '#ffb6c1';
  // }

  @Input() member!: FamilyMember;
  @Input() isChild: boolean = false;
  @Output() addParent = new EventEmitter<FamilyMember>();
  @Output() addSpouse = new EventEmitter<FamilyMember>();
  @Output() addChild = new EventEmitter<FamilyMember>();
  @Output() editMember = new EventEmitter<FamilyMember>();
  @Output() deleteMember = new EventEmitter<FamilyMember>();

  getGenderColor(): string {
    return this.member && this.member.gender === 'male' ? '#add8e6' : '#ffb6c1';
  }

  getSiblings(): FamilyMember[] {
    if (this.member && this.member.parents && this.member.parents.length > 0) {
      const parent = this.member.parents[0];
      return parent.children ? parent.children.filter(child => child !== this.member) : [];
    }
    return [];
  }
}

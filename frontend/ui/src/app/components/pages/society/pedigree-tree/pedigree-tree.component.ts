import { Component } from '@angular/core';
import {FamilyMember} from "../../../../interfaces/models/society/family-member";
import {FamilyTreeService} from "../../../../services/models/society/family-tree/family-tree.service";

@Component({
  selector: 'app-pedigree-tree',
  templateUrl: './pedigree-tree.component.html',
  styleUrl: './pedigree-tree.component.css'
})
export class PedigreeTreeComponent {
  // rootMember: FamilyMember | null = null;
  // showRootForm = false;
  //
  // rootMemberData: FamilyMember = { name: '', born: 0, gender: '', parents: [], spouses: [], children: [] };
  //
  // // Function to create a new root member
  // createRootMember(member: FamilyMember) {
  //   this.rootMember = member;
  //   this.showRootForm = false;
  // }
  //
  // // Function to delete the root member
  // deleteRootMember() {
  //   this.rootMember = null;
  // }
  //
  // // Add a parent, spouse, or child node
  // addMember(type: 'parent' | 'spouse' | 'child', member: FamilyMember) {
  //   if (type === 'parent' && this.rootMember) {
  //     this.rootMember.parents = this.rootMember.parents || [];
  //     this.rootMember.parents.push(member);
  //   } else if (type === 'spouse' && this.rootMember) {
  //     this.rootMember.spouses = this.rootMember.spouses || [];
  //     this.rootMember.spouses.push(member);
  //   } else if (type === 'child' && this.rootMember) {
  //     this.rootMember.children = this.rootMember.children || [];
  //     this.rootMember.children.push(member);
  //   }
  // }
  //
  // // Function to handle member editing
  // editMember(member: FamilyMember, updatedInfo: FamilyMember) {
  //   Object.assign(member, updatedInfo);
  // }
  //
  // // Function to handle member deletion
  // deleteMember(member: FamilyMember) {
  //   if (this.rootMember) {
  //     // Remove from parents
  //     this.rootMember.parents = this.rootMember.parents?.filter(p => p !== member);
  //     // Remove from spouses
  //     this.rootMember.spouses = this.rootMember.spouses?.filter(s => s !== member);
  //     // Remove from children
  //     this.rootMember.children = this.rootMember.children?.filter(c => c !== member);
  //   }
  // }
  //
  // // Toggle the root form visibility
  // toggleRootForm() {
  //   this.showRootForm = !this.showRootForm;
  // }

  rootMember: FamilyMember | null = null;
  showForm = false;
  formType: 'root' | 'parent' | 'spouse' | 'child' | 'edit' = 'root';
  selectedMember: FamilyMember | null = null;

  constructor(private familyTreeService: FamilyTreeService) {}

  ngOnInit() {
    this.familyTreeService.getRootMember().subscribe(root => {
      this.rootMember = root;
    });
  }

  openForm(type: 'root' | 'parent' | 'spouse' | 'child' | 'edit', member?: FamilyMember) {
    this.formType = type;
    this.selectedMember = member || null;
    this.showForm = true;
  }

  handleFormSubmit(member: FamilyMember) {
    switch (this.formType) {
      case 'root':
        this.familyTreeService.setRootMember(member);
        break;
      case 'parent':
        this.familyTreeService.addParent(this.selectedMember!, member);
        break;
      case 'spouse':
        this.familyTreeService.addSpouse(this.selectedMember!, member);
        break;
      case 'child':
        this.familyTreeService.addChild(this.selectedMember!, member);
        break;
      case 'edit':
        this.familyTreeService.updateMember(this.selectedMember!, member);
        break;
    }
    this.showForm = false;
    this.selectedMember = null;
  }

  deleteMember(member: FamilyMember) {
    this.familyTreeService.deleteMember(member);
    if (member === this.rootMember) {
      this.rootMember = null;
    }
  }
}

import { Component } from '@angular/core';
import {FamilyMember} from "../../../../interfaces/models/society/family-member";
import {FamilyTreeService} from "../../../../services/models/society/family/family-tree.service";

@Component({
  selector: 'app-family-ng-tree',
  templateUrl: './family-ng-tree.component.html',
  styleUrl: './family-ng-tree.component.css'
})
export class FamilyNgTreeComponent {
  allMembers: FamilyMember[] = [];
  rootMembers: FamilyMember[] = [];
  searchTerm: string = '';

  constructor(private familyTreeService: FamilyTreeService) {}

  ngOnInit() {
    this.familyTreeService.getFamilyMembers().subscribe(members => {
      this.allMembers = members;
      this.rootMembers = members.filter(m => m.children.length === 0);
    });
  }

  onMemberAdded(member: FamilyMember) {
    this.familyTreeService.addMember(member);
  }

  onSearch() {
    if (this.searchTerm) {
      this.rootMembers = this.familyTreeService.searchMember(this.searchTerm);
    } else {
      this.rootMembers = this.allMembers.filter(m => m.children.length === 0);
    }
  }
}

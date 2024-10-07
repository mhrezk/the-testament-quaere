import {Component, Input} from '@angular/core';
import {FamilyMember} from "../../../../interfaces/models/society/family-member";
import {FamilyTreeService} from "../../../../services/models/society/family/family-tree.service";

@Component({
  selector: 'app-family-member-node',
  templateUrl: './family-member-node.component.html',
  styleUrl: './family-member-node.component.css'
})
export class FamilyMemberNodeComponent {
  @Input() member!: FamilyMember;
  @Input() allMembers: FamilyMember[] = [];

  isEditing = false;
  editMember: FamilyMember = { ...this.member };

  constructor(private familyTreeService: FamilyTreeService) {}

  toggleEdit() {
    this.isEditing = !this.isEditing;
    this.editMember = { ...this.member };
  }

  onSave() {
    this.familyTreeService.updateMember(this.editMember);
    this.isEditing = false;
  }

  onCancel() {
    this.isEditing = false;
  }

  onDelete() {
    this.familyTreeService.deleteMember(this.member.id);
  }

  onAddSpouse() {
    const spouseId = prompt('Enter spouse ID:');
    if (spouseId) {
      this.familyTreeService.addSpouse(this.member.id, spouseId);
    }
  }

  onAddChild() {
    const childId = prompt('Enter child ID:');
    if (childId) {
      this.familyTreeService.addChild(this.member.id, childId);
    }
  }

  getMemberById(id: string): FamilyMember | undefined {
    return this.allMembers.find(m => m.id === id);
  }
}

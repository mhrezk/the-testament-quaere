import { Injectable } from '@angular/core';
import {FamilyMember} from "../../../../interfaces/models/society/family-member";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FamilyTreeService {

  private familyMembers = new BehaviorSubject<FamilyMember[]>([]);

  getFamilyMembers(): Observable<FamilyMember[]> {
    return this.familyMembers.asObservable();
  }

  addMember(member: FamilyMember): void {
    const currentMembers = this.familyMembers.value;
    this.familyMembers.next([...currentMembers, member]);
  }

  updateMember(updatedMember: FamilyMember): void {
    const currentMembers = this.familyMembers.value;
    const index = currentMembers.findIndex(m => m.id === updatedMember.id);
    if (index !== -1) {
      currentMembers[index] = updatedMember;
      this.familyMembers.next([...currentMembers]);
    }
  }

  deleteMember(id: string): void {
    const currentMembers = this.familyMembers.value;
    this.familyMembers.next(currentMembers.filter(m => m.id !== id));
  }

  addSpouse(memberId: string, spouseId: string): void {
    const currentMembers = this.familyMembers.value;
    const member = currentMembers.find(m => m.id === memberId);
    const spouse = currentMembers.find(m => m.id === spouseId);
    if (member && spouse) {
      member.spouses.push(spouseId);
      spouse.spouses.push(memberId);
      this.familyMembers.next([...currentMembers]);
    }
  }

  addChild(parentId: string, childId: string): void {
    const currentMembers = this.familyMembers.value;
    const parent = currentMembers.find(m => m.id === parentId);
    if (parent) {
      parent.children.push(childId);
      this.familyMembers.next([...currentMembers]);
    }
  }

  searchMember(name: string): FamilyMember[] {
    const currentMembers = this.familyMembers.value;
    return currentMembers.filter(m =>
      m.firstName.toLowerCase().includes(name.toLowerCase()) ||
      m.lastName.toLowerCase().includes(name.toLowerCase())
    );
  }
}

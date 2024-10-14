import { Injectable } from '@angular/core';
import {FamilyMember} from "../../../../interfaces/models/society/family-member";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FamilyTreeService {
  private rootMember = new BehaviorSubject<FamilyMember | null>(null);

  getRootMember(): Observable<FamilyMember | null> {
    return this.rootMember.asObservable();
  }

  setRootMember(member: FamilyMember) {
    this.rootMember.next(member);
  }

  addParent(child: FamilyMember, parent: FamilyMember) {
    if (!child.parents) {
      child.parents = [];
    }
    child.parents.push(parent);
    this.updateTree();
  }

  addSpouse(member: FamilyMember, spouse: FamilyMember) {
    if (!member.spouses) {
      member.spouses = [];
    }
    member.spouses.push(spouse);
    this.updateTree();
  }

  addChild(parent: FamilyMember, child: FamilyMember) {
    if (!parent.children) {
      parent.children = [];
    }
    parent.children.push(child);
    this.updateTree();
  }

  updateMember(oldMember: FamilyMember, newMember: FamilyMember) {
    Object.assign(oldMember, newMember);
    this.updateTree();
  }

  deleteMember(member: FamilyMember) {
    const root = this.rootMember.value;
    if (root === member) {
      this.rootMember.next(null);
    } else {
      this.deleteMemberRecursive(root!, member);
      this.updateTree();
    }
  }

  private deleteMemberRecursive(current: FamilyMember, memberToDelete: FamilyMember): boolean {
    if (current.parents) {
      current.parents = current.parents.filter(p => p !== memberToDelete);
      for (const parent of current.parents) {
        if (this.deleteMemberRecursive(parent, memberToDelete)) {
          return true;
        }
      }
    }
    if (current.spouses) {
      current.spouses = current.spouses.filter(s => s !== memberToDelete);
    }
    if (current.children) {
      current.children = current.children.filter(c => c !== memberToDelete);
      for (const child of current.children) {
        if (this.deleteMemberRecursive(child, memberToDelete)) {
          return true;
        }
      }
    }
    return false;
  }

  private updateTree() {
    this.rootMember.next({...this.rootMember.value!});
  }
}

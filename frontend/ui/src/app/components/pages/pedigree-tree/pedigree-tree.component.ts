import { Component } from '@angular/core';
import {FamilyMember} from "../../../interfaces/models/society/family-member";
import {FamilyTreeService} from "../../../services/models/society/family/family-tree.service";

@Component({
  selector: 'app-pedigree-tree',
  templateUrl: './pedigree-tree.component.html',
  styleUrl: './pedigree-tree.component.css'
})
export class PedigreeTreeComponent {
  // familyTree: FamilyMember[] = [
  //   {
  //     id: 1,
  //     name: 'Robert Shanks',
  //     birth: '1781',
  //     death: '1871',
  //     place: 'Ireland/Petersburg, VA',
  //     spouses: [
  //       { id: 4, name: 'Elizabeth Shanks', birth: '1795', death: '1871', place: 'Ireland/Petersburg, VA' }
  //     ],
  //     children: [
  //       {
  //         id: 2,
  //         name: 'James Shanks',
  //         birth: '1831',
  //         death: '1884',
  //         place: 'Petersburg, VA',
  //         children: [
  //           {
  //             id: 3,
  //             name: 'Clifford Shanks',
  //             birth: '1862',
  //             death: '1906',
  //             place: 'Petersburg, VA'
  //           }
  //         ]
  //       }
  //     ]
  //   }
  // ];
  //
  // selectedMember: FamilyMember | null = null;
  // searchQuery: string = '';
  // searchResults: FamilyMember[] = [];
  //
  // // Function to select a member for editing
  // selectMember(member: FamilyMember) {
  //   // Create a clone to avoid editing the original member before save
  //   this.selectedMember = { ...member };
  // }
  //
  // // Function to save updated member details
  // saveMember(updatedMember: FamilyMember) {
  //   const updateFamilyMember = (members: FamilyMember[]) => {
  //     for (let i = 0; i < members.length; i++) {
  //       if (members[i].id === updatedMember.id) {
  //         members[i] = updatedMember; // Update the matching member
  //         return;
  //       }
  //       // Recursively check children and spouses for nested updates
  //       if (members[i].children) updateFamilyMember(members[i].children);
  //       if (members[i].spouses) updateFamilyMember(members[i].spouses);
  //     }
  //   };
  //
  //   // Update the family tree with the modified member details
  //   updateFamilyMember(this.familyTree);
  //
  //   // Clear the selected member (hide the modal)
  //   this.selectedMember = null;
  // }
  //
  // // Optional: A function to cancel the edit process
  // cancelEdit() {
  //   this.selectedMember = null;
  // }
  //
  // // Save the edited details
  // // saveMember(member: FamilyMember) {
  // //   this.updateMember(this.familyTree, member);
  // //   this.selectedMember = null; // Deselect after saving
  // // }
  //
  // // Update family member in the tree
  // private updateMember(tree: FamilyMember[], updatedMember: FamilyMember) {
  //   tree.forEach((member, index) => {
  //     if (member.id === updatedMember.id) {
  //       tree[index] = updatedMember;
  //     }
  //     if (member.children) {
  //       this.updateMember(member.children, updatedMember);
  //     }
  //     if (member.spouses) {
  //       this.updateMember(member.spouses, updatedMember);
  //     }
  //   });
  // }
  //
  // // Add a new family member
  // addMember(parentId: number) {
  //   const newMember: FamilyMember = {
  //     id: Date.now(),
  //     name: 'New Member',
  //     birth: 'Unknown',
  //     death: 'Unknown',
  //     place: 'Unknown'
  //   };
  //
  //   this.addToTree(this.familyTree, parentId, newMember);
  // }
  //
  // private addToTree(tree: FamilyMember[], parentId: number, newMember: FamilyMember) {
  //   tree.forEach(member => {
  //     if (member.id === parentId) {
  //       if (!member.children) {
  //         member.children = [];
  //       }
  //       member.children.push(newMember);
  //     }
  //     if (member.children) {
  //       this.addToTree(member.children, parentId, newMember);
  //     }
  //   });
  // }
  //
  // // Add a spouse
  // addSpouse(memberId: number) {
  //   const newSpouse: FamilyMember = {
  //     id: Date.now(),
  //     name: 'New Spouse',
  //     birth: 'Unknown',
  //     death: 'Unknown',
  //     place: 'Unknown'
  //   };
  //
  //   this.addSpouseToTree(this.familyTree, memberId, newSpouse);
  // }
  //
  // private addSpouseToTree(tree: FamilyMember[], memberId: number, newSpouse: FamilyMember) {
  //   tree.forEach(member => {
  //     if (member.id === memberId) {
  //       if (!member.spouses) {
  //         member.spouses = [];
  //       }
  //       member.spouses.push(newSpouse);
  //     }
  //     if (member.children) {
  //       this.addSpouseToTree(member.children, memberId, newSpouse);
  //     }
  //   });
  // }
  //
  // addChild(parentId: number) {
  //   const parent = this.familyTree.find(p => p.id === parentId || (p.spouses && p.spouses.some(s => s.id === parentId)));
  //   if (parent) {
  //     const newChild = {
  //       id: Date.now(), // Unique ID for new child
  //       name: 'New Child',
  //       birth: '',
  //       death: '',
  //       place: ''
  //     };
  //     parent.children = parent.children || [];
  //     parent.children.push(newChild);
  //   }
  // }
  //
  // // Search for a family member by name
  // searchMembers() {
  //   this.searchResults = [];
  //   this.searchFamilyTree(this.familyTree, this.searchQuery.toLowerCase());
  // }
  //
  // private searchFamilyTree(tree: FamilyMember[], query: string) {
  //   tree.forEach(member => {
  //     if (member.name.toLowerCase().includes(query)) {
  //       this.searchResults.push(member);
  //     }
  //     if (member.children) {
  //       this.searchFamilyTree(member.children, query);
  //     }
  //     if (member.spouses) {
  //       this.searchFamilyTree(member.spouses, query);
  //     }
  //   });
  // }

  // familyTree = [
  //   {
  //     id: 1,
  //     name: 'John Doe',
  //     birth: '1950',
  //     death: '2020',
  //     place: 'City, Country',
  //     spouses: [
  //       {
  //         id: 2,
  //         name: 'Jane Doe',
  //         birth: '1955',
  //         death: '2021',
  //         place: 'City, Country',
  //       }
  //     ],
  //     children: [
  //       {
  //         id: 3,
  //         name: 'Child Doe',
  //         birth: '1980',
  //         death: '2040',
  //         place: 'City, Country',
  //         spouses: [],
  //         children: []
  //       }
  //     ]
  //   }
  // ];
  //
  // selectedMember: any;
  //
  // selectMember(member: any) {
  //   this.selectedMember = { ...member };
  // }
  //
  // saveMember(member: any) {
  //   // Logic to update the member in the familyTree
  //   const updatedMember = this.familyTree.find((m) => m.id === member.id ||
  //     m.children.some(c => c.id === member.id));
  //   if (updatedMember) {
  //     if (updatedMember.id === member.id) {
  //       Object.assign(updatedMember, member);
  //     } else {
  //       const child = updatedMember.children.find(c => c.id === member.id);
  //       if (child) Object.assign(child, member);
  //     }
  //   }
  //   this.selectedMember = null;
  // }
  //
  // cancelEdit() {
  //   this.selectedMember = null;
  // }
  //
  // addSpouse(personId: number) {
  //   const person = this.findPersonById(personId);
  //   if (person) {
  //     const newSpouse = {
  //       id: Date.now(), // Unique ID for new spouse
  //       name: 'New Spouse',
  //       birth: '',
  //       death: '',
  //       place: ''
  //     };
  //     person.spouses = person.spouses || [];
  //     person.spouses.push(newSpouse);
  //   }
  // }
  //
  // addChild(parentId: number) {
  //   const parent = this.findPersonById(parentId);
  //   if (parent) {
  //     const newChild = {
  //       id: Date.now(), // Unique ID for new child
  //       name: 'New Child',
  //       birth: '',
  //       death: '',
  //       place: '',
  //       spouses: [],
  //       children: []
  //     };
  //     parent.children = parent.children || [];
  //     parent.children.push(newChild);
  //   }
  // }
  //
  // findPersonById(personId: number) {
  //   let person = this.familyTree.find(p => p.id === personId);
  //   if (!person) {
  //     for (const family of this.familyTree) {
  //       if (family.children) {
  //         person = family.children.find(c => c.id === personId);
  //         if (person) return person;
  //       }
  //     }
  //   }
  //   return person;
  // }

  // familyTree = [
  //   {
  //     id: 1,
  //     name: 'John Doe',
  //     gender: 'male',
  //     birth: 1965,
  //     death: 2020,
  //     place: 'New York',
  //     spouses: [],
  //     children: []
  //   }
  // ];
  // showEditModal: boolean = false;
  // selectedMember: any = null;
  // searchQuery: string = '';
  //
  // // Opens the edit modal
  // selectMember(member: any) {
  //   this.showEditModal = true;
  //   this.selectedMember = { ...member }; // Create a copy of the member for editing
  // }
  //
  // // Save updated member
  // saveMember() {
  //   if (this.selectedMember) {
  //     const updateMember = (members: any[]): boolean => {
  //       for (let member of members) {
  //         if (member.id === this.selectedMember.id) {
  //           Object.assign(member, this.selectedMember);
  //           return true;
  //         }
  //
  //         if (member.spouses) {
  //           for (let spouse of member.spouses) {
  //             if (spouse.id === this.selectedMember.id) {
  //               Object.assign(spouse, this.selectedMember);
  //               return true;
  //             }
  //           }
  //         }
  //
  //         if (member.children) {
  //           if (updateMember(member.children)) return true;
  //         }
  //       }
  //       return false;
  //     };
  //
  //     updateMember(this.familyTree);
  //     this.selectedMember = null; // Close the modal after saving
  //   }
  // }
  //
  // // Add spouse to the member
  // addSpouse(memberId: number) {
  //   const newSpouse = {
  //     id: Date.now(),
  //     name: 'New Spouse',
  //     gender: 'female',
  //     birth: null,
  //     death: null,
  //     place: '',
  //     spouses: [],
  //     children: []
  //   };
  //
  //   const addToMember = (members: any[]): boolean => {
  //     for (let member of members) {
  //       if (member.id === memberId) {
  //         if (!member.spouses) member.spouses = [];
  //         member.spouses.push(newSpouse);
  //         return true;
  //       }
  //
  //       if (member.children) {
  //         if (addToMember(member.children)) return true;
  //       }
  //     }
  //     return false;
  //   };
  //
  //   addToMember(this.familyTree);
  // }
  //
  // // Add child to the member
  // addChild(parentId: number) {
  //   const newChild = {
  //     id: Date.now(),
  //     name: 'New Child',
  //     gender: 'male',
  //     birth: null,
  //     death: null,
  //     place: '',
  //     spouses: [],
  //     children: []
  //   };
  //
  //   const addToParent = (members: any[]): boolean => {
  //     for (let member of members) {
  //       if (member.id === parentId) {
  //         if (!member.children) member.children = [];
  //         member.children.push(newChild);
  //         return true;
  //       }
  //
  //       if (member.spouses) {
  //         for (let spouse of member.spouses) {
  //           if (spouse.id === parentId) {
  //             if (!spouse.children) spouse.children = [];
  //             spouse.children.push(newChild);
  //             return true;
  //           }
  //         }
  //       }
  //
  //       if (member.children) {
  //         if (addToParent(member.children)) return true;
  //       }
  //     }
  //     return false;
  //   };
  //
  //   addToParent(this.familyTree);
  // }
  //
  // // Delete member
  // deleteMember(memberId: number) {
  //   const removeMember = (members: any[]): boolean => {
  //     for (let i = 0; i < members.length; i++) {
  //       if (members[i].id === memberId) {
  //         members.splice(i, 1);
  //         return true;
  //       }
  //
  //       if (members[i].spouses) {
  //         members[i].spouses = members[i].spouses.filter(spouse => spouse.id !== memberId);
  //       }
  //
  //       if (members[i].children) {
  //         if (removeMember(members[i].children)) return true;
  //       }
  //     }
  //     return false;
  //   };
  //
  //   removeMember(this.familyTree);
  // }
  //
  // // Determine node color based on gender
  // getColor(gender: string) {
  //   return gender === 'male' ? '#ADD8E6' : '#FFB6C1'; // Light blue for male, light pink for female
  // }


}

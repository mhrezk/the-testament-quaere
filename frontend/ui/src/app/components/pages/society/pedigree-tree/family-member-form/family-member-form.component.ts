import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FamilyMember} from "../../../../../interfaces/models/society/family-member";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-family-member-form',
  templateUrl: './family-member-form.component.html',
  styleUrl: './family-member-form.component.css'
})
export class FamilyMemberFormComponent {
  @Input() formType!: 'root' | 'parent' | 'spouse' | 'child' | 'edit';
  @Input() member: FamilyMember | null = null;
  @Output() formSubmit = new EventEmitter<FamilyMember>();

  familyForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.familyForm = this.fb.group({
      name: ['', Validators.required],
      born: ['', Validators.required],
      died: [''],
      location: [''],
      gender: ['', Validators.required]
    });
  }

  ngOnInit() {
    if (this.formType === 'edit' && this.member) {
      this.familyForm.patchValue(this.member);
    }
  }

  onSubmit() {
    if (this.familyForm.valid) {
      this.formSubmit.emit(this.familyForm.value);
    }
  }
}

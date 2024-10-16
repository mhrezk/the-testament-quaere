import { Component, OnDestroy } from "@angular/core";
import {
  ControlValueAccessor,
  NG_VALUE_ACCESSOR,
  FormGroup,
  FormControl,
  FormArray
} from "@angular/forms";
import {
  FamilyTreeModel,
  createFamilyTreeGroup,
  createFamilyTreeControl,
  updateFamilyTreeFormGroup
} from "./family-tree-form.types";
import { Subscription } from "rxjs";

@Component({
  selector: 'app-family-tree-balkan-form',
  templateUrl: './family-tree-form.component.html',
  styleUrl: './family-tree-form.component.css',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: FamilyTreeFormComponent,
      multi: true
    }
  ]
})
export class FamilyTreeFormComponent implements ControlValueAccessor, OnDestroy {
  form: FormGroup;
  onChange;
  formChangesSubscription: Subscription;

  writeValue(v: FamilyTreeModel) {
    if (this.form) {
      updateFamilyTreeFormGroup(this.form, v);
    } else {
      this.form = createFamilyTreeGroup(v);
    }
  }

  registerOnChange(fn) {
    this.onChange = fn;
    this.formChangesSubscription = this.form.valueChanges.subscribe(x =>
      this.onChange(x)
    );
  }

  registerOnTouched(fn) {}

  addChild() {
    (this.form.get("children") as FormArray).push(
      createFamilyTreeControl({ name: "", age: 0, children: [] })
    );
  }

  ngOnDestroy() {
    this.formChangesSubscription.unsubscribe();
  }
}

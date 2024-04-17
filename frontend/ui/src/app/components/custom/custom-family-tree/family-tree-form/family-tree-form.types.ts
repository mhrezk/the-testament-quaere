import { FormControl, FormGroup, FormArray } from '@angular/forms';
import { Observable } from 'rxjs';

export interface FamilyTreeModel {
  name: string;
  age: number;
  children: FamilyTreeModel[];
}

export const createFamilyTreeGroup = (model: FamilyTreeModel) => {
  return new FormGroup({
    name: new FormControl(model.name),
    age: new FormControl(model.age),
    children: new FormArray(model.children.map(child => new FormControl(child)))
  });
}

export const createFamilyTreeControl = (model: FamilyTreeModel) => new FormControl(model);

export const updateFamilyTreeFormGroup = (group: FormGroup, newModel: FamilyTreeModel) => {
  group.get('name').setValue(newModel.name);
  group.get('age').setValue(newModel.age);
  const childrenArray = group.get('children') as FormArray;
  while (childrenArray.controls.length > 0) {
    childrenArray.removeAt(0);
  }
  newModel.children.forEach(child => childrenArray.push(createFamilyTreeControl(child)));
}

// export class GenericTypedFormControl<T> extends FormControl {
//   value: T;
//   valueChanges: Observable<T>;
//   setValue(
//     v: T,
//     options: {
//       onlySelf?: boolean;
//       emitEvent?: boolean;
//       emitModelToViewChange?: boolean;
//       emitViewToModelChange?: boolean;
//     } = {}
//   ) {
//     super.setValue(v, options);
//   }
//   patchValue(
//     v: T,
//     options: {
//       onlySelf?: boolean;
//       emitEvent?: boolean;
//       emitModelToViewChange?: boolean;
//       emitViewToModelChange?: boolean;
//     } = {}
//   ) {
//     super.patchValue(v, options);
//   }
// }

// export class GenericTypedFormGroup<T> extends FormGroup {
//   value: T;
//   valueChanges: Observable<T>;
//   setValue(
//     v: T,
//     options: {
//       onlySelf?: boolean;
//       emitEvent?: boolean;
//       emitModelToViewChange?: boolean;
//       emitViewToModelChange?: boolean;
//     } = {}
//   ) {
//     super.setValue(v, options);
//   }
//   patchValue(
//     v: T,
//     options: {
//       onlySelf?: boolean;
//       emitEvent?: boolean;
//       emitModelToViewChange?: boolean;
//       emitViewToModelChange?: boolean;
//     } = {}
//   ) {
//     super.patchValue(v, options);
//   }
// }

// export type FamilyTreeFormControl = GenericTypedFormControl<FamilyTreeModel>;

import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { createFamilyTreeControl } from './family-tree-form/family-tree-form.types';

@Component({
  selector: 'app-custom-family-tree-balkan',
  templateUrl: './custom-family-tree.component.html',
  styleUrl: './custom-family-tree.component.css'
})
export class CustomFamilyTreeComponent {
  control = createFamilyTreeControl({
    name: 'Zack',
    age: 32,
    children: [
      {
        name: 'Kylie',
        age: 18,
        children: [
          {
            name: 'Kaydan',
            age: 1,
            children: []
          }
        ]
      },
      {
        name: 'Eli',
        age: 10,
        children: []
      },
      {
        name: 'Luke',
        age: 9,
        children: []
      },
      {
        name: 'Claire',
        age: 3,
        children: []
      },
      {
        name: 'Averie',
        age: 1,
        children: []
      }
    ]
  });
  name = 'Angular';

  updateFormValue() {
    this.control.setValue({
      name: 'Homer',
      age: 45,
      children: [
        {
          name: 'Bart',
          age: 12,
          children: []
        },
        {
          name: 'Lisa',
          age: 9,
          children: []
        },
        {
          name: 'Maggie',
          age: 1,
          children: []
        }
      ]
    });
  }
}

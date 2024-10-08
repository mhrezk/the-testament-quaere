import {Component, ElementRef, Input, SimpleChanges} from '@angular/core';
import * as d3 from 'd3';
import {FamilyMember} from "../../../interfaces/models/society/family-member";

@Component({
  selector: 'app-pedigree-tree',
  templateUrl: './pedigree-tree.component.html',
  styleUrl: './pedigree-tree.component.css'
})
export class PedigreeTreeComponent {
  familyData = {
    "name": "Clifford Shanks",
    "born": 1862,
    "died": 1906,
    "location": "Petersburg, VA",
    "parents": [
      {
        "name": "James Shanks",
        "born": 1831,
        "died": 1884,
        "location": "Petersburg, VA",
        "parents": [
          {
            "name": "Robert Shanks",
            "born": 1781,
            "died": 1871,
            "location": "Ireland/Petersburg, VA"
          },
          {
            "name": "Elizabeth Shanks",
            "born": 1795,
            "died": 1871,
            "location": "Ireland/Petersburg, VA"
          }
        ]
      },
      {
        "name": "Ann Emily Brown",
        "born": 1826,
        "died": 1866,
        "location": "Brunswick/Petersburg, VA",
        "parents": [
          {
            "name": "Henry Brown",
            "born": 1792,
            "died": 1845,
            "location": "Montgomery, NC"
          },
          {
            "name": "Sarah Houchins",
            "born": 1793,
            "died": 1882,
            "location": "Montgomery, NC"
          }
        ]
      }
    ]
  };
}

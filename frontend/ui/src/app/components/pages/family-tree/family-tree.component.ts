import {Component, OnInit} from '@angular/core';
import FamilyTree from '../../../../assets/balkanapp/familytree';

@Component({
  selector: 'app-family-tree',
  templateUrl: './family-tree.component.html',
  styleUrl: './family-tree.component.css'
})
export class FamilyTreeComponent implements OnInit {
  ngOnInit() {
    const tree = document.getElementById('tree');
    if (tree) {
      let family = new FamilyTree(tree, {
        nodeBinding: {
          field_0: "name",
          field_1: "location",
          img_0: "img"
        },
      });

      family.load([
        { id: 1, pids: [2], name: "Amber McKenzie", location: "Germany", gender: "female", img: "https://cdn.balkan.app/shared/2.jpg"  },
        { id: 2, pids: [1], name: "Ava Field", gender: "male", img: "https://cdn.balkan.app/shared/m30/5.jpg" },
        { id: 3, mid: 1, fid: 2, name: "Peter Stevens", gender: "male", img: "https://cdn.balkan.app/shared/m10/2.jpg" },
        { id: 4, mid: 1, fid: 2, name: "Savin Stevens", gender: "male", img: "https://cdn.balkan.app/shared/m10/1.jpg"  },
        { id: 5, mid: 1, fid: 2, name: "Emma Stevens", gender: "female", img: "https://cdn.balkan.app/shared/w10/3.jpg" }
      ]);
    }
  }
}

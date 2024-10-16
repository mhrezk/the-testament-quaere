import {Component, ElementRef, Input, OnInit, Renderer2} from '@angular/core';
import FamilyTree from '../../../../assets/balkanapp/familytree';
import {Family} from "../../../interfaces/models/society/family";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-family-tree-balkan',
  templateUrl: './family-tree-balkan.component.html',
  styleUrl: './family-tree-balkan.component.css'
})
export class FamilyTreeBalkanComponent implements OnInit {
  @Input() familyNode: Family;
  family: any[] = [];
  name: string;

  showTree: boolean = false;

  constructor(private router: Router,
              private renderer: Renderer2,
              private element: ElementRef) {
  }

  ngOnInit() {
    //FamilyTree.templates['tommy_female']['button'] = FamilyTree.templates["tommy_male"]['button'] = `<foreignobject class="node" x="20" y="10" width="100" height="50"><button onclick="alert(this.getAttribute('data-text'));" data-text="{val}">Click me!</button></foreignobject>`;
    // this.family = [
    //   { id: 1, pids: [2], name: "John Doe", age: 45, location: "Ireland", gender: "male" },
    //   { id: 2, pids: [1], name: "Jane Doe", age: 42, location: "America", gender: "female" }
    // ];

    // console.log(this.familyNode);
    // const tree = document.getElementById('tree');
    // if (tree) {
    //   let family = new FamilyTree(tree, {
    //     nodeBinding: {
    //       field_0: "name",
    //       field_1: "age",
    //       field_2: "location",
    //       img_0: "img"
    //     },
    //   });

      // family.load([
      //   {
      //     id: 1,
      //     pids: this.familyNode.spouseIDs,
      //     mid: this.familyNode.motherID,
      //     fid: this.familyNode.fatherID,
      //     name: this.familyNode.firstName + " " + this.familyNode.secondName,
      //     location: this.familyNode.nation,
      //     Gender: this.familyNode.gender,
      //     img: this.familyNode.imageURL
      //   }
      // ])

      // family.load([
      //   { id: 1, pids: [2], name: "Amber McKenzie", location: "Germany", gender: "female", img: "https://cdn.balkan.app/shared/2.jpg"  },
      //   { id: 2, pids: [1], name: "Ava Field", gender: "male", img: "https://cdn.balkan.app/shared/m30/5.jpg" },
      //   { id: 3, mid: 1, fid: 2, name: "Peter Stevens", gender: "male", img: "https://cdn.balkan.app/shared/m10/2.jpg" },
      //   { id: 4, mid: 1, fid: 2, name: "Savin Stevens", gender: "male", img: "https://cdn.balkan.app/shared/m10/1.jpg"  },
      //   { id: 5, mid: 1, fid: 2, name: "Emma Stevens", gender: "female", img: "https://cdn.balkan.app/shared/w10/3.jpg" }
      // ]);
    //}
  }

  onSubmit(personForm: NgForm) {
    // FamilyTree.templates['tommy_male']['field_0'] =
    //   '<text class="field_0" style="font-size: 20px;" fill="#ffffff" x="125" y="30" text-anchor="middle">{val}</text>';
    // FamilyTree.templates['tommy_male']['field_1'] =
    //   '<text class="field_1" style="font-size: 14px;" fill="#ffffff" x="125" y="50" text-anchor="middle">{val}</text>';
    // FamilyTree.templates['tommy_male']['field_2'] =
    //   '<text class="field_2" style="font-size: 14px;" fill="#ffffff" x="125" y="70" text-anchor="middle">{val}</text>';
    // FamilyTree.templates['tommy_male']['field_3'] =
    //   '<text class="field_3" style="font-size: 14px;" fill="#ffffff" x="125" y="90" text-anchor="middle">{val}</text>';
    // FamilyTree.templates['tommy_female']['field_0'] =
    //   '<text class="field_0" style="font-size: 20px;" fill="#ffffff" x="125" y="30" text-anchor="middle">{val}</text>';
    // FamilyTree.templates['tommy_female']['field_1'] =
    //   '<text class="field_1" style="font-size: 14px;" fill="#ffffff" x="125" y="50" text-anchor="middle">{val}</text>';
    // FamilyTree.templates['tommy_female']['field_2'] =
    //   '<text class="field_2" style="font-size: 14px;" fill="#ffffff" x="125" y="70" text-anchor="middle">{val}</text>';
    // FamilyTree.templates['tommy_female']['field_3'] =
    //   '<text class="field_3" style="font-size: 14px;" fill="#ffffff" x="125" y="90" text-anchor="middle">{val}</text>';
    const tree = document.getElementById('tree');
    if (tree) {
      let familyTree = new FamilyTree(tree, {
        template: "tommy",
        nodeBinding: {
          field_0: "name",
          // field_1: "location",
          // field_2: "age",
          img_0: "img",
        },
        editForm: {
          buttons:  {
            pdf: null,
            share: null
          }
        },
        nodeTreeMenu: true,
        //nodeMouseClick: FamilyTree.action.none,
        mode: "dark"
      });
      // familyTree.on('click', (sender, node) => {
      //   //familyTree.removeNode(node.node.id);
      //   console.log(node);
      //   console.log(sender);
      //   console.log(familyTree.getNode(node.node.id));
      //   console.log(familyTree.get(node.node.id));
      //   // this.family.splice(node.node.id - 1, 1);
      //   // familyTree.load(this.family);
      // });
      let node: any = {
        id: this.family.length + 1,
        name: personForm.value.firstName + " " + personForm.value.secondName,
        // age: personForm.value.age,
        // location: personForm.value.location,
        gender: personForm.value.gender,
        img: "../../../assets/images/people/default.png"
      }
      // familyTree.on("update", (sender, node) => {
      //   console.log(sender);
      //   console.log(node);
      //   console.log(node.updateNodesData);
      // })
      familyTree.onUpdateNode((args) => {
        console.log(args);
      })
      this.name = node.name;
      console.log(personForm.value.name);
      this.family.push(node);
      familyTree.load(this.family);
      console.log(this.family);
      console.log(node);
    }
    personForm.reset();
    this.showTree = true;
  }

  // addButtonToNodes() {
  //   const nodes = document.querySelectorAll('.node-male');  // Adjust to match your node class/id
  //   nodes.forEach((node: any) => {
  //     const button = this.renderer.createElement('button');
  //     const text = this.renderer.createText('Click Me');
  //     this.renderer.appendChild(button, text);
  //
  //     // Add event listener to the button
  //     // this.renderer.listen(button, 'click', () => {
  //     //   alert(`Button clicked on node ${node.id}`);
  //     // });
  //
  //     // Style the button
  //     this.renderer.setStyle(button, 'position', 'absolute');
  //     this.renderer.setStyle(button, 'bottom', '10px');
  //     this.renderer.setStyle(button, 'right', '10px');
  //
  //     // Append button to the node
  //     this.renderer.appendChild(node, button);
  //   });
  // }

  addParent(childID: number) {
    const tree = document.getElementById('tree');
    if (tree) {
      let familyTree = new FamilyTree(tree, {
        nodeBinding: {
          field_0: "name",
          field_1: "age",
          img_0: "img"
        }
      });
      let parent: any = {
        id: this.family.length + 1,
        name: "Fenix Schneider",
        age: 20,
        location: "Ireland",
        gender: "Male",
        img: "../../../assets/images/people/default.png"
      }
      // familyTree.on('click', (sender, node) => {
      //   this.family[node.node.id - 1].fid = parent.id;
      //   this.family.push(parent);
      //   familyTree.load(this.family);
      // })
      // this.family[childID - 1].fid = parent.id;
      // this.family.push(parent);
      // familyTree.load(this.family);

      familyTree.addParentNode(childID, "fid", parent, () => {
        console.log("Parent added successfully");
      });
      console.log(this.family);
    }
  }

  routeToHome() {
    this.router.navigateByUrl("/home");
  }
}

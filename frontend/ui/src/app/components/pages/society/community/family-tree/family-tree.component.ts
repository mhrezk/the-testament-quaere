import {Component, ElementRef, OnInit, Renderer2} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import FamilyTree from "../../../../../../assets/balkanapp/familytree";
import {FamilyService} from "../../../../../services/models/society/family/family.service";
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {Family} from "../../../../../interfaces/models/society/family";
import {DataState} from "../../../../../enums/data-state";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {catchError} from "rxjs/operators";
import {AppState} from "../../../../../interfaces/app-state";
import {Gender} from "../../../../../enums/gender";
import { v4 as uuidv4 } from 'uuid';
import * as convert from 'xml-js';
import {Root} from "../../../../../interfaces/models/society/family-node/root";
import {Attributes2} from "../../../../../interfaces/models/society/family-node/attributes2";
import {faCircleArrowLeft} from "@fortawesome/free-solid-svg-icons";
import {NodeAttributes} from "../../../../../interfaces/models/society/family-node/node-attributes";

@Component({
  selector: 'app-family-tree',
  templateUrl: './family-tree.component.html',
  styleUrl: './family-tree.component.css'
})
export class FamilyTreeComponent implements OnInit {
  defaultImageURL = "../../../assets/images/people/default.png";

  faCircleArrowLeft = faCircleArrowLeft;

  balkanFamily: any[] = [];
  family: Family;
  families: Family[] = [];
  name: string;

  node: object;
  root: Root;

  clickCount: number = 0;
  countSubject = new BehaviorSubject<number>(0);
  count$ = this.countSubject.asObservable();
  arrayLengthSubject = new BehaviorSubject<number>(0);
  arrayLength$ = this.arrayLengthSubject.asObservable();

  communityName: string;
  communityID: string;
  communitySize: string;

  showTree: boolean = false;
  showSuccessfulSave: boolean = false;
  showWarning: boolean = false;

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  constructor(private familyService: FamilyService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  async ngOnInit() {
    this.clickCount = 0;
    //XML to JSON Conversion
    // var xml =
    //   '<?xml version="1.0" encoding="utf-8"?>' +
    //   '<note importance="high" logged="true">' +
    //   '    <title>Happy</title>' +
    //   '    <todo>Work</todo>' +
    //   '    <todo>Play</todo>' +
    //   '</note>';
    // var result1 = this.convert.xml2json(xml, {compact: true, spaces: 4});
    // var result2 = this.convert.xml2json(xml, {compact: false, spaces: 4});

    const tree = document.getElementById('tree');

    this.activatedRoute.paramMap.subscribe(params => {
      this.communityID = params.get("id");
    });

    this.activatedRoute.paramMap.subscribe(params => {
      this.communityName = params.get("name");
    });

    this.activatedRoute.paramMap.subscribe(params => {
      this.communitySize = params.get("size");
    });

    //OR

    // this.activatedRoute.params.subscribe(params => {
    //   this.communityName = params['name'];
    // });

    //console.log(this.communityName + " " + this.communityID);
    if(+this.communitySize > 0) {
      await this.getFamiliesByCommunityName(this.communityName);
      this.countSubject.next(this.families.length);
      this.initializeFamilialTree(tree);
    }
  }

  getFamiliesByCommunity(communityName: string) {
    this.appState$ = this.familyService.getFamiliesByCommunityName$(communityName)
      .pipe(
        map((result) => {
          this.families = result.data.dataRetrieved;
          this.dataSubject.next(result); //stores result in dataSubject to be used in another method or later
          this.countSubject.next(this.families.length);
          return {
            dataState: DataState.LOADED,
            appData: result,
          };
        }),
        startWith({
          dataState: DataState.LOADING,
          // appData: null
        }),
        catchError((caughtError: string) => {
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
  }

  async getFamiliesByCommunityName(communityName: string) {
    const result = await this.familyService.getFamiliesByCommunityName(communityName).toPromise();
    this.families = [];
    this.family = null;
    this.families = result.data.dataRetrieved;
    this.arrayLengthSubject.next(this.families.length);
    console.log(this.families);
  }

  getFamiliesCountByCommunityName(communityName: string) {
    this.familyService.getFamiliesByCommunityName(communityName).subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    );
  }

  onSubmit(personForm: NgForm) {
    const tree = document.getElementById('tree');
    this.initializeFamilialTreeWithForm(tree, personForm);
    personForm.reset();
    //this.showTree = true;
    this.communitySize = "1";
  }

  initializeFamilialTreeWithForm(tree: HTMLElement, personForm: NgForm) {
    if (tree) {
      let familyTree = new FamilyTree(tree, {
        template: "tommy",
        nodeBinding: {
          field_0: "name",
          field_1: "gender",
          img_0: "img",
        },
        editForm: {
          buttons:  {
            //pdf: null,
            share: null
          },
          //For picture uploading
          //generateElementsFromFields: false,
          photoBinding: "photo",
          elements: [
            { type: 'textbox', label: 'Photo Url', binding: 'photo', btn: 'Upload' },
            { type: 'textbox', label: 'Full Name', binding: 'name' }
          ],
          addMore: null,
          addMoreBtn: null,
          addMoreFieldName: null,
        },
        nodeTreeMenu: true,
        mode: "dark"
      });
      let node: Attributes2 = {
        id: uuidv4(), //generates UUID
        name: personForm.value.firstName + " " + personForm.value.secondName,
        gender: personForm.value.gender,
        img: this.defaultImageURL
      }

      //For picture uploading
      // familyTree.editUI.on('element-btn-click', function (sender, args) {
      //   FamilyTree.fileUploadDialog(function (file) {
      //     var data = new FormData();
      //     data.append('files', file);
      //
      //     fetch('/Home/UploadPhoto', {
      //       method: 'POST',
      //       body: data
      //     })
      //       .then(response => {
      //         response.json().then(responseData => {
      //           args.input.value = responseData.url;
      //           sender.setAvatar(responseData.url);
      //         });
      //       });
      //   });
      // });
      this.name = node.name;
      this.initializeFamily(node, personForm);
      this.families.push(this.family);
      this.balkanFamily.push(node);
      this.countSubject.next(this.balkanFamily.length);
      this.clickCount = 0;
      familyTree.load(this.balkanFamily);
      console.log(this.family);
      console.log(this.families);
      console.log(node);
      familyTree.on("updated", (sender, node) => {
        console.log("Updated");
        // console.log(sender);
        // console.log(node);
        let result1 = convert.xml2json(familyTree.getXML(), {compact: true, spaces: 4});
        console.log(result1);
        this.root = JSON.parse(result1);
        console.log(this.root);
        console.log(this.root.nodes.node);
        this.families = this.root.nodes.node.map(
          node => this.convertToFamilyObject(node._attributes)
        )
        this.balkanFamily = this.root.nodes.node;
        this.countSubject.next(this.root.nodes.node.length)
        console.log(this.balkanFamily);
        console.log(this.families);
        console.log(JSON.stringify(this.families));
        this.clickCount = 0;
      })

      familyTree.on("init", (sender) => {
        console.log(familyTree.get(node.id));
        console.log(node.name);
        this.family = this.convertToFamilyObject(node);
        this.clickCount = 0;
        console.log(this.family);
        console.log(familyTree.get(this.convertToNodeObject(this.family).id));
      })

      familyTree.on("dbclick", (sender, node) => {
        if(this.families.length === 1) {
          this.clickCount = 0;
          familyTree.destroy();
          this.balkanFamily = [];
          this.families = [];
          this.familyService.deleteAllFamilies().subscribe();
          this.countSubject.next(0);
          this.routeToCommunityDescription(this.communityID, this.communityName);
        }
      })

      // familyTree.on("click", (sender, node) => {
      //   console.log(familyTree.get(node.node.id));
      // })
    }
  }

  initializeFamilialTree(tree: HTMLElement) {
    if (tree) {
      let familyTree = new FamilyTree(tree, {
        template: "tommy",
        nodeBinding: {
          field_0: "name",
          field_1: "gender",
          img_0: "img",
        },
        editForm: {
          buttons:  {
            share: null
          },
          photoBinding: "photo",
          elements: [
            { type: 'textbox', label: 'Photo Url', binding: 'photo', btn: 'Upload' },
            { type: 'textbox', label: 'Full Name', binding: 'name' }
          ],
          addMore: null,
          addMoreBtn: null,
          addMoreFieldName: null,
        },
        nodeTreeMenu: true,
        mode: "dark"
      });
      this.balkanFamily = this.families.map(
        family => this.convertToNodeObject(family)
      );
      this.clickCount = 0;
      familyTree.load(this.balkanFamily);

      familyTree.on("updated", (sender, node) => {
        this.families = [];
        let result1 = convert.xml2json(familyTree.getXML(), {compact: true, spaces: 4});
        console.log(result1);
        this.root = JSON.parse(result1);
        console.log(this.root.nodes.node);
        let familyNodes = this.root.nodes.node;
        if (Array.isArray(familyNodes)) {
          this.families = familyNodes.map((node: NodeAttributes) => this.convertToFamilyObject(node._attributes));
        } else if (familyNodes) {
          // If it's a single object, wrap it in an array
          this.families = [this.convertToFamilyObject((familyNodes as NodeAttributes)._attributes)];
          this.balkanFamily = this.families.map(
            family => this.convertToNodeObject(family)
          );
          familyTree.load(this.balkanFamily);
        }
        // else {
        //   console.warn("No valid family nodes found.");
        //   this.families = []; // Reset if no valid nodes
        //   window.location.reload();
        // }
        this.countSubject.next(this.families.length)
        this.clickCount = 0;
        console.log(this.families);
      })

      familyTree.on("dbclick", (sender, node) => {
        if(this.balkanFamily.length === 1) {
          this.clickCount = 0;
          familyTree.destroy();
          this.balkanFamily = [];
          this.families = [];
          this.familyService.deleteAllFamilies().subscribe();
          this.countSubject.next(0);
          this.routeToCommunityDescription(this.communityID, this.communityName);
        }
      })
    }
  }

  initializeFamily(node: any, personForm: NgForm) {
    this.family = {
      stringID: node.id,
      firstName: personForm.value.firstName,
      secondName: personForm.value.secondName,
      gender: personForm.value.gender,
      communityName: this.communityName,
      imageURL: node.img
    }
  }

  convertToFamilyObject(node: Attributes2) {
    let family: Family = {
      spouseIDs: node.pids || [], // Default to an empty array if undefined
      motherID: node.mid || "",   // Default to an empty string if undefined
      fatherID: node.fid || "",    // Default to an empty string if undefined
      firstName: "",
      secondName: "",
      gender: Gender[node.gender.toUpperCase() as keyof typeof Gender],
      stringID: node.id,
      communityName: this.communityName,
      imageURL: node.img || ""
    };

    if (node.name) { // Check if node.name is not empty, null, or undefined
      let arrayName = node.name.split(" ");
      family.firstName = arrayName[0];
      family.secondName = arrayName[1] || ""; // Set secondName to empty string if it doesn't exist
    }

    if(node.fid === "undefined") {
      family.fatherID = "";
    }

    if(node.mid === "undefined") {
      family.motherID = "";
    }
    return family;
  }

  convertToNodeObject(family: Family) {
    let node: Attributes2 = {
      id: family.stringID,
      pids: family.spouseIDs,
      mid: family.motherID,
      fid: family.fatherID,
      name: family.firstName + " " + family.secondName,
      gender: family.gender.toLowerCase(),
      img: family.imageURL
    }
    return node;
  }

  saveTree(families: Family[], communityID: number, communitySize: number) {
    this.familyService.saveFamilies(families, communityID, communitySize).subscribe();
  }

  saveFamilialTree() {
    ++this.clickCount;
    this.showSuccessfulSave = true;
    if(this.families.length <= 0) {
      this.families.push(this.family);
      this.familyService.saveFamilies(this.families, +this.communityID, this.families.length).subscribe();
    } else {
      this.familyService.saveFamilies(this.families, +this.communityID, this.families.length).subscribe();
    }
    setTimeout(() => {
      this.showSuccessfulSave = false;
    }, 3000);
  }

  exit(communityID: string, communityName: string) {
    this.router.navigate(['communities', communityID, communityName])
  }

  routeToCommunityDescription(communityID: string, communityName: string) {
    this.router.navigate(['communities', communityID, communityName]);
    //const submit = document.getElementById("exit")
    // if(this.clickCount < 1) {
    //   this.showWarning = true;
    //   //console.log(submit);
    // }
    // submit.addEventListener("click", () => {
    //   this.router.navigate(['communities', communityID, communityName])
    // });
  }
}

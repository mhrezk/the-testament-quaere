import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {Community} from "../../../../../interfaces/models/society/family-node/community";
import {CommunityService} from "../../../../../services/models/society/community/community.service";
import {Router} from "@angular/router";
import {faEdit, faCircleArrowLeft} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-community-display',
  templateUrl: './community-display.component.html',
  styleUrl: './community-display.component.css'
})
export class CommunityDisplayComponent implements OnInit {
  isEditing: boolean = false;

  communityID: number;
  communityName: string;

  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  selectedCommunity: Community;

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  constructor(private communityService: CommunityService,
              private router: Router) {
  }

  ngOnInit() {
    this.communityService.getCommunityID$.subscribe(
      result => this.communityID = result
    );
    this.communityService.getName$.subscribe(
      result => this.communityName = result
    );
    this.getCommunityByID(this.communityID);
  }

  getCommunityByID(communityID: number) {
    this.communityService.getCommunityByID(communityID).subscribe(result => {
      this.selectedCommunity = result.data.datumRetrieved;
    });
  }

  updateDescription() {
    this.communityService.updateCommunity(this.selectedCommunity.id, this.selectedCommunity).subscribe();
    this.isEditing = false;
  }

  routeToFamilyTree(communityID: number, communityName: string, communitySize: number) {
    if(communitySize === null) {
      communitySize = 0;
    }
    console.log(communitySize);
    this.router.navigate(['/family-tree', communityID, communityName, communitySize]);
  }

  routeToCommunities() {
    this.router.navigateByUrl("/communities");
  }
}

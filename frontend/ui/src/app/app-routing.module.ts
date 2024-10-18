import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/pages/home/home.component";
import {CalendarComponent} from "./components/pages/calendar/calendar.component";
import {PersonComponent} from "./components/pages/society/person/person.component";
import {RaceComponent} from "./components/pages/history/race/race.component";
import {TimelineComponent} from "./components/pages/history/timeline/timeline.component";
import {FamilyTreeBalkanComponent} from "./components/pages/family-tree-balkan/family-tree-balkan.component";
import {HierarchyComponent} from "./components/pages/hierarchy/hierarchy/hierarchy.component";
import {CustomFamilyTreeComponent} from "./components/custom/custom-family-tree/custom-family-tree.component";
import {OrganizationComponent} from "./components/pages/politics/organization/organization.component";
import {NationComponent} from "./components/pages/places/nation/nation.component";
//import {PedigreeTreeComponent} from "./components/pages/society/pedigree-tree/pedigree-tree.component";
import {PersonDetailsComponent} from "./components/pages/society/person-details/person-details.component";
import {
  TimelineDisplayComponent
} from "./components/pages/history/timeline/timeline-display/timeline-display.component";
import {CommunityComponent} from "./components/pages/society/community/community.component";
import {
  CommunityDisplayComponent
} from "./components/pages/society/community/community-display/community-display.component";
import {FamilyTreeComponent} from "./components/pages/society/community/family-tree/family-tree.component";
import {RaceDisplayComponent} from "./components/pages/history/race/race-display/race-display.component";
//import {LineageTreeComponent} from "./components/pages/society/lineage-tree/lineage-tree.component";

const routes: Routes = [
  {
    path: "",
    pathMatch: "full",
    redirectTo: "home"
  },
  {
    path: "home",
    component: HomeComponent
  },
  {
    path: "calendar",
    component: CalendarComponent
  },
  {
    path: "people",
    component: PersonComponent
  },
  {
    path: "person-details/:id/:firstName/:secondName",
    component: PersonDetailsComponent
  },
  {
    path: "nations",
    component: NationComponent
  },
  {
    path: "races",
    component: RaceComponent
  },
  {
    path: "races/:id/:name",
    component: RaceDisplayComponent
  },
  {
    path: "organizations",
    component: OrganizationComponent
  },
  {
    path: "timelines",
    component: TimelineComponent
  },{
    path: "timeline-display/:name",
    component: TimelineDisplayComponent
  },
  {
    path: "family-tree-balkan",
    component: FamilyTreeBalkanComponent
  },
  {
    path: "communities",
    component: CommunityComponent
  },
  {
    path: "communities/:id/:name",
    component: CommunityDisplayComponent
  },
  {
    path: "family-tree-balkan/:firstName/:secondName",
    component: FamilyTreeBalkanComponent
  },
  {
    path: "family-tree/:id/:name/:size",
    component: FamilyTreeComponent
  },
  {
    path: "hierarchy",
    component: HierarchyComponent
  },
  {
    path: "custom-family-tree-balkan",
    component: CustomFamilyTreeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

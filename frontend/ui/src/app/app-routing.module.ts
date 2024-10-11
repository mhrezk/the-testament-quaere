import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/pages/home/home.component";
import {CalendarComponent} from "./components/pages/calendar/calendar.component";
import {PersonComponent} from "./components/pages/society/person/person.component";
import {RaceComponent} from "./components/pages/history/race/race.component";
import {TimelineComponent} from "./components/pages/history/timeline/timeline.component";
import {FamilyTreeComponent} from "./components/pages/family-tree/family-tree.component";
import {HierarchyComponent} from "./components/pages/hierarchy/hierarchy/hierarchy.component";
import {CustomFamilyTreeComponent} from "./components/custom/custom-family-tree/custom-family-tree.component";
import {OrganizationComponent} from "./components/pages/politics/organization/organization.component";
import {NationComponent} from "./components/pages/places/nation/nation.component";
import {PedigreeTreeComponent} from "./components/pages/pedigree-tree/pedigree-tree.component";
import {PersonDetailsComponent} from "./components/pages/society/person-details/person-details.component";
import {
  TimelineDisplayComponent
} from "./components/pages/history/timeline/timeline-display/timeline-display.component";

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
    path: "family-tree",
    component: PedigreeTreeComponent
  },
  {
    path: "hierarchy",
    component: HierarchyComponent
  },
  {
    path: "custom-family-tree",
    component: CustomFamilyTreeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

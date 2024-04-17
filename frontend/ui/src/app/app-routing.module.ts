import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/pages/home/home.component";
import {CalendarComponent} from "./components/pages/calendar/calendar.component";
import {PersonComponent} from "./components/pages/society/person/person.component";
import {RaceComponent} from "./components/pages/history/race/race.component";
import {TimelineComponent} from "./components/pages/timeline/timeline.component";
import {FamilyTreeComponent} from "./components/pages/family-tree/family-tree.component";
import {HierarchyComponent} from "./components/pages/hierarchy/hierarchy/hierarchy.component";

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
    path: "races",
    component: RaceComponent
  },
  {
    path: "timeline",
    component: TimelineComponent
  },
  {
    path: "family-tree",
    component: FamilyTreeComponent
  },
  {
    path: "hierarchy",
    component: HierarchyComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

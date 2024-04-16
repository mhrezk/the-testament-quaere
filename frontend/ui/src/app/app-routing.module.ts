import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/pages/home/home.component";
import {CalendarComponent} from "./components/pages/calendar/calendar.component";
import {PersonComponent} from "./components/pages/society/person/person.component";
import {RaceComponent} from "./components/pages/history/race/race.component";
import {TimelineComponent} from "./components/pages/timeline/timeline.component";

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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

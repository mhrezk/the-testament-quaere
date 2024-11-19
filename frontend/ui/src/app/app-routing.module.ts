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
import {SubRaceComponent} from "./components/pages/history/race/sub-race/sub-race.component";
import {
  SubRaceDisplayComponent
} from "./components/pages/history/race/sub-race/sub-race-display/sub-race-display.component";
import {RaceDisplayEditComponent} from "./components/pages/history/race/race-display-edit/race-display-edit.component";
import {LanguageComponent} from "./components/pages/history/language/language.component";
import {
  LanguageDisplayComponent
} from "./components/pages/history/language/language-display/language-display.component";
import {
  LanguageDisplayEditComponent
} from "./components/pages/history/language/language-display-edit/language-display-edit.component";
import {LetterComponent} from "./components/pages/history/letter/letter.component";
import {AuthorComponent} from "./components/pages/history/library/author/author.component";
import {
  AuthorDisplayComponent
} from "./components/pages/history/library/author/author-display/author-display.component";
import {BookComponent} from "./components/pages/history/library/author/book/book.component";
import {BookDisplayComponent} from "./components/pages/history/library/author/book/book-display/book-display.component";
import {ChapterComponent} from "./components/pages/history/library/author/book/chapter/chapter.component";
import {
  ChapterDisplayComponent
} from "./components/pages/history/library/author/book/chapter/chapter-display/chapter-display.component";
import {
  ChapterDisplayEditComponent
} from "./components/pages/history/library/author/book/chapter/chapter-display-edit/chapter-display-edit.component";
import {BookReadComponent} from "./components/pages/history/library/author/book/chapter/book-read/book-read.component";
import {CardComponent} from "./components/pages/card/card.component";
import {TradingCardComponent} from "./components/pages/card/trading-card/trading-card.component";
import {NationDetailsComponent} from "./components/pages/places/nation-details/nation-details.component";
import {DiagramComponent} from "./components/shared/diagram/diagram.component";
import {ReligionComponent} from "./components/pages/dogma/religion/religion.component";
import {ReligionDisplayComponent} from "./components/pages/dogma/religion/religion-display/religion-display.component";
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
    path: "religions",
    component: ReligionComponent
  },
  {
    path: "religions/:id/:name",
    component: ReligionDisplayComponent
  },
  {
    path: "people",
    component: PersonComponent
  },
  {
    path: "person-details/:firstName/:secondName",
    component: PersonDetailsComponent
  },
  {
    path: "nation-details/:id/:nationName",
    component: NationDetailsComponent
  },
  {
    path: "nations",
    component: NationComponent
  },
  {
    path: "languages",
    component: LanguageComponent
  },
  {
    path: "languages/:id/:name",
    component: LanguageDisplayComponent
  },
  {
    path: "languages/:id/:name/edit",
    component: LanguageDisplayEditComponent
  },
  {
    path: "letters/language/:id/:name/:size",
    component: LetterComponent
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
    path: "races/:id/:name/edit",
    component: RaceDisplayEditComponent
  },
  {
    path: "sub-races/race/:id/:name",
    component: SubRaceComponent
  },
  {
    path: "sub-races/:id/:name",
    component: SubRaceDisplayComponent
  },
  {
    path: "organizations",
    component: OrganizationComponent
  },
  {
    path: "cards",
    component: CardComponent
  },
  {
    path: "trading-cards",
    component: TradingCardComponent
  },
  {
    path: "library",
    component: AuthorComponent
  },
  {
    path: "author/:id/:firstName/:lastName",
    component: AuthorDisplayComponent
  },
  {
    path: "author/:id/:firstName/:lastName/books",
    component: BookComponent
  },
  {
    path: "author/:id/:firstName/:lastName/books/:bookID",
    component: BookDisplayComponent
  },
  {
    path: "author/:id/:firstName/:lastName/books/book/:bookID/:name/chapters/read/all",
    component: BookReadComponent
  },
  {
    path: "author/:id/:firstName/:lastName/books/book/:bookID/:name/chapters",
    component: ChapterComponent
  },
  {
    path: "author/:id/:firstName/:lastName/books/book/:bookID/:name/chapters/chapter/:chapterID",
    component: ChapterDisplayComponent
  },
  {
    path: "author/:id/:firstName/:lastName/books/book/:bookID/:name/chapters/chapter/:chapterID/edit",
    component: ChapterDisplayEditComponent
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

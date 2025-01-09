import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RouterModule, RouterOutlet } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { NgOptimizedImage } from "@angular/common";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { DragDropModule } from '@angular/cdk/drag-drop';

import {MatToolbarModule} from "@angular/material/toolbar";
import { MatButtonModule } from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatSidenavModule} from "@angular/material/sidenav";
import { MatListModule } from "@angular/material/list";
import { MatInputModule } from "@angular/material/input";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatDialogModule } from "@angular/material/dialog";
import { MatTooltipModule } from '@angular/material/tooltip';

import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";

import { TreeModule } from 'primeng/tree';
import { OrganizationChartModule }
  from 'primeng/organizationchart';

import { NgxSimpleTextEditorModule } from 'ngx-simple-text-editor';
import {NgxPaginationModule} from "ngx-pagination";
import { QuillModule } from "ngx-quill";
//import { PaginationModule } from 'ngx-pagination';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomSidenavComponent } from './components/custom/custom-sidenav/custom-sidenav.component';
import { HomeComponent } from './components/pages/home/home.component';
import { CalendarComponent } from './components/pages/calendar/calendar.component';
import { PersonComponent } from './components/pages/society/person/person.component';
import { RaceComponent } from './components/pages/history/race/race.component';
import {ModalModule} from "./modules/modal/modal.module";
import { TimelineComponent } from './components/pages/history/timeline/timeline.component';
import { FamilyTreeBalkanComponent } from './components/pages/family-tree-balkan/family-tree-balkan.component';
import { InspectorComponent } from './components/pages/hierarchy/inspector/inspector.component';
import {OrganizationChartComponent} from "./components/pages/hierarchy/organization-chart/organization-chart.component";
import { HierarchyComponent } from './components/pages/hierarchy/hierarchy/hierarchy.component';
import { CustomFamilyTreeComponent } from './components/custom/custom-family-tree/custom-family-tree.component';
import { FamilyTreeFormComponent } from './components/custom/custom-family-tree/family-tree-form/family-tree-form.component';
import { OrganizationComponent } from './components/pages/politics/organization/organization.component';
import { NationComponent } from './components/pages/places/nation/nation.component';
import { PersonDetailsComponent } from './components/pages/society/person-details/person-details.component';
import {MatCard, MatCardActions, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatOption} from "@angular/material/autocomplete";
import {MatSelect} from "@angular/material/select";
import { FilterPipe } from './pipes/filter/filter.pipe';
import { PersonDetailsEditComponent } from './components/pages/society/person-details/person-details-edit/person-details-edit.component';
import { TimelineDisplayComponent } from './components/pages/history/timeline/timeline-display/timeline-display.component';
import { PersonDetailsBiographyComponent } from './components/pages/society/person-details/person-details-biography/person-details-biography.component';
import { PedigreeTreeComponent } from './components/pages/society/pedigree-tree/pedigree-tree.component';
import { FamilyMemberNodeComponent } from './components/pages/society/pedigree-tree/family-member-node/family-member-node.component';
import { FamilyMemberFormComponent } from './components/pages/society/pedigree-tree/family-member-form/family-member-form.component';
import { CommunityComponent } from './components/pages/society/community/community.component';
import { FamilyTreeComponent } from './components/pages/society/community/family-tree/family-tree.component';
import { CommunityDisplayComponent } from './components/pages/society/community/community-display/community-display.component';
import { RaceDisplayComponent } from './components/pages/history/race/race-display/race-display.component';
import { SubRaceComponent } from './components/pages/history/race/sub-race/sub-race.component';
import { SubRaceDisplayComponent } from './components/pages/history/race/sub-race/sub-race-display/sub-race-display.component';
import { RaceDisplayEditComponent } from './components/pages/history/race/race-display-edit/race-display-edit.component';
import { LanguageComponent } from './components/pages/history/language/language.component';
import { LetterComponent } from './components/pages/history/letter/letter.component';
import { LanguageDisplayComponent } from './components/pages/history/language/language-display/language-display.component';
import { LanguageDisplayEditComponent } from './components/pages/history/language/language-display-edit/language-display-edit.component';
import { AuthorComponent } from './components/pages/history/library/author/author.component';
import { BookComponent } from './components/pages/history/library/author/book/book.component';
import { ChapterComponent } from './components/pages/history/library/author/book/chapter/chapter.component';
import { AuthorDisplayComponent } from './components/pages/history/library/author/author-display/author-display.component';
import { AuthorBiographyComponent } from './components/pages/history/library/author/author-display/author-biography/author-biography.component';
import { AuthorDisplayEditComponent } from './components/pages/history/library/author/author-display/author-display-edit/author-display-edit.component';
import { BookDisplayComponent } from './components/pages/history/library/author/book/book-display/book-display.component';
import { BookDisplayEditComponent } from './components/pages/history/library/author/book/book-display-edit/book-display-edit.component';
import { BookDescriptionComponent } from './components/pages/history/library/author/book/book-description/book-description.component';
import { ChapterDisplayComponent } from './components/pages/history/library/author/book/chapter/chapter-display/chapter-display.component';
import { ChapterDisplayEditComponent } from './components/pages/history/library/author/book/chapter/chapter-display-edit/chapter-display-edit.component';
import { ChapterTextComponent } from './components/pages/history/library/author/book/chapter/chapter-text/chapter-text.component';
import { BookReadComponent } from './components/pages/history/library/author/book/chapter/book-read/book-read.component';
import { CardComponent } from './components/pages/card/card.component';
import { TradingCardComponent } from './components/pages/card/trading-card/trading-card.component';
import { NationDetailsComponent } from './components/pages/places/nation-details/nation-details.component';
import { NationDetailsEditComponent } from './components/pages/places/nation-details/nation-details-edit/nation-details-edit.component';
import { NationDetailsHistoryComponent } from './components/pages/places/nation-details/nation-details-history/nation-details-history.component';
import { TooltipComponent } from './components/shared/tooltip/tooltip.component';
import { DiagramComponent } from './components/shared/diagram/diagram.component';
import { FruitComponent } from './components/shared/fruit/fruit.component';
import { ReligionComponent } from './components/pages/dogma/religion/religion.component';
import { ReligionDisplayComponent } from './components/pages/dogma/religion/religion-display/religion-display.component';
import { ReligionDescriptionComponent } from './components/pages/dogma/religion/religion-description/religion-description.component';
import { ReligionDisplayEditComponent } from './components/pages/dogma/religion/religion-display-edit/religion-display-edit.component';
import { ProphetComponent } from './components/pages/dogma/religion/prophet/prophet.component';
import { DeityComponent } from './components/pages/dogma/religion/deity/deity.component';
import { ProphetDisplayComponent } from './components/pages/dogma/religion/prophet/prophet-display/prophet-display.component';
import { DeityDisplayComponent } from './components/pages/dogma/religion/deity/deity-display/deity-display.component';
import { DeityDescriptionComponent } from './components/pages/dogma/religion/deity/deity-description/deity-description.component';
import { ProphetDescriptionComponent } from './components/pages/dogma/religion/prophet/prophet-description/prophet-description.component';
import { ProphetDisplayEditComponent } from './components/pages/dogma/religion/prophet/prophet-display-edit/prophet-display-edit.component';
import { DeityDisplayEditComponent } from './components/pages/dogma/religion/deity/deity-display-edit/deity-display-edit.component';
import { OrganizationDisplayComponent } from './components/pages/politics/organization/organization-display/organization-display.component';
import { OrganizationDescriptionComponent } from './components/pages/politics/organization/organization-description/organization-description.component';
import { OrganizationDisplayEditComponent } from './components/pages/politics/organization/organization-display-edit/organization-display-edit.component';
import { EncyclopediaComponent } from './components/pages/encyclopedia/encyclopedia.component';
import { AngelComponent } from './components/pages/encyclopedia/angel/angel.component';
import { DemonComponent } from './components/pages/encyclopedia/demon/demon.component';
import { FaeComponent } from './components/pages/encyclopedia/fae/fae.component';
import { MineralComponent } from './components/pages/encyclopedia/mineral/mineral.component';
import { MaterialComponent } from './components/pages/encyclopedia/material/material.component';
import { AnimalComponent } from './components/pages/encyclopedia/animal/animal.component';
import { PlantComponent } from './components/pages/encyclopedia/plant/plant.component';
import { BacteriaComponent } from './components/pages/encyclopedia/bacteria/bacteria.component';
import { FungiComponent } from './components/pages/encyclopedia/fungi/fungi.component';
import { ProtozoanComponent } from './components/pages/encyclopedia/protozoan/protozoan.component';
import { VirusComponent } from './components/pages/encyclopedia/virus/virus.component';
import { FungusComponent } from './components/pages/encyclopedia/fungus/fungus.component';
import { DiseaseComponent } from './components/pages/encyclopedia/disease/disease.component';
import { MedicationComponent } from './components/pages/encyclopedia/medication/medication.component';
import { MedicineComponent } from './components/pages/encyclopedia/medicine/medicine.component';
import { ParasiteComponent } from './components/pages/encyclopedia/parasite/parasite.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomSidenavComponent,
    HomeComponent,
    CalendarComponent,
    PersonComponent,
    RaceComponent,
    TimelineComponent,
    FamilyTreeBalkanComponent,
    OrganizationChartComponent,
    InspectorComponent,
    HierarchyComponent,
    CustomFamilyTreeComponent,
    FamilyTreeFormComponent,
    OrganizationComponent,
    NationComponent,
    PersonDetailsComponent,
    FilterPipe,
    PersonDetailsEditComponent,
    TimelineDisplayComponent,
    PersonDetailsBiographyComponent,
    PedigreeTreeComponent,
    FamilyMemberNodeComponent,
    FamilyMemberFormComponent,
    CommunityComponent,
    FamilyTreeComponent,
    CommunityDisplayComponent,
    RaceDisplayComponent,
    SubRaceComponent,
    SubRaceDisplayComponent,
    RaceDisplayEditComponent,
    LanguageComponent,
    LetterComponent,
    LanguageDisplayComponent,
    LanguageDisplayEditComponent,
    AuthorComponent,
    BookComponent,
    ChapterComponent,
    AuthorDisplayComponent,
    AuthorBiographyComponent,
    AuthorDisplayEditComponent,
    BookDisplayComponent,
    BookDisplayEditComponent,
    BookDescriptionComponent,
    ChapterDisplayComponent,
    ChapterDisplayEditComponent,
    ChapterTextComponent,
    BookReadComponent,
    CardComponent,
    TradingCardComponent,
    NationDetailsComponent,
    NationDetailsEditComponent,
    NationDetailsHistoryComponent,
    TooltipComponent,
    DiagramComponent,
    FruitComponent,
    ReligionComponent,
    ReligionDisplayComponent,
    ReligionDescriptionComponent,
    ReligionDisplayEditComponent,
    ProphetComponent,
    DeityComponent,
    ProphetDisplayComponent,
    DeityDisplayComponent,
    DeityDescriptionComponent,
    ProphetDescriptionComponent,
    ProphetDisplayEditComponent,
    DeityDisplayEditComponent,
    OrganizationDisplayComponent,
    OrganizationDescriptionComponent,
    OrganizationDisplayEditComponent,
    EncyclopediaComponent,
    AngelComponent,
    DemonComponent,
    FaeComponent,
    MineralComponent,
    MaterialComponent,
    AnimalComponent,
    PlantComponent,
    BacteriaComponent,
    FungiComponent,
    ProtozoanComponent,
    VirusComponent,
    FungusComponent,
    DiseaseComponent,
    MedicationComponent,
    MedicineComponent,
    ParasiteComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    DragDropModule,
    ReactiveFormsModule,
    RouterOutlet,
    HttpClientModule,
    MatToolbarModule,
    MatTooltipModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatDialogModule,
    MatInputModule,
    MatFormFieldModule,
    NgOptimizedImage,
    RouterModule,
    BrowserAnimationsModule,
    NgxSimpleTextEditorModule,
    FontAwesomeModule,
    OrganizationChartModule,
    TreeModule,
    NgxPaginationModule,
    ModalModule,
    MatCardActions,
    MatCardContent,
    MatCardTitle,
    MatCard,
    MatOption,
    MatSelect,
    QuillModule.forRoot()
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    //ModalService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

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

import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";

import { TreeModule } from 'primeng/tree';
import { OrganizationChartModule }
  from 'primeng/organizationchart';

import { NgxSimpleTextEditorModule } from 'ngx-simple-text-editor';
import {NgxPaginationModule} from "ngx-pagination";
//import { QuillModule } from "ngx-quill";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomSidenavComponent } from './components/custom/custom-sidenav/custom-sidenav.component';
import { HomeComponent } from './components/pages/home/home.component';
import { CalendarComponent } from './components/pages/calendar/calendar.component';
import { PersonComponent } from './components/pages/society/person/person.component';
import { RaceComponent } from './components/pages/history/race/race.component';
import {ModalModule} from "./modules/modal/modal.module";
import { TimelineComponent } from './components/pages/timeline/timeline.component';
import { FamilyTreeComponent } from './components/pages/family-tree/family-tree.component';
import { InspectorComponent } from './components/pages/hierarchy/inspector/inspector.component';
import {OrganizationChartComponent} from "./components/pages/hierarchy/organization-chart/organization-chart.component";
import { HierarchyComponent } from './components/pages/hierarchy/hierarchy/hierarchy.component';
import { CustomFamilyTreeComponent } from './components/custom/custom-family-tree/custom-family-tree.component';
import { FamilyTreeFormComponent } from './components/custom/custom-family-tree/family-tree-form/family-tree-form.component';
import { OrganizationComponent } from './components/pages/politics/organization/organization.component';
import { NationComponent } from './components/pages/places/nation/nation.component';
import { FamilyMemberNodeComponent } from './components/pages/pedigree-tree/family-member-node/family-member-node.component';
import { PedigreeTreeComponent } from './components/pages/pedigree-tree/pedigree-tree.component';
import { PersonDetailsComponent } from './components/pages/society/person-details/person-details.component';
import {MatCard, MatCardActions, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatOption} from "@angular/material/autocomplete";
import {MatSelect} from "@angular/material/select";
import { FilterPipe } from './pipes/filter/filter.pipe';
import { FamilyMemberFormComponent } from './components/pages/pedigree-tree/family-member-form/family-member-form.component';
import { FamilyNgTreeComponent } from './components/pages/pedigree-tree/family-ng-tree/family-ng-tree.component';
import { PersonDetailsEditComponent } from './components/pages/society/person-details/person-details-edit/person-details-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomSidenavComponent,
    HomeComponent,
    CalendarComponent,
    PersonComponent,
    RaceComponent,
    TimelineComponent,
    FamilyTreeComponent,
    OrganizationChartComponent,
    InspectorComponent,
    HierarchyComponent,
    CustomFamilyTreeComponent,
    FamilyTreeFormComponent,
    OrganizationComponent,
    NationComponent,
    FamilyMemberNodeComponent,
    PedigreeTreeComponent,
    PersonDetailsComponent,
    FilterPipe,
    FamilyMemberFormComponent,
    FamilyNgTreeComponent,
    PersonDetailsEditComponent
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
    MatSelect
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    //ModalService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

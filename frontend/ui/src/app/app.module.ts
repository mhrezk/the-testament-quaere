import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { RouterModule, RouterOutlet } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { NgOptimizedImage } from "@angular/common";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import {MatToolbarModule} from "@angular/material/toolbar";
import { MatButtonModule } from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatSidenavModule} from "@angular/material/sidenav";
import { MatListModule } from "@angular/material/list";
import { MatInputModule } from "@angular/material/input";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatDialogModule } from "@angular/material/dialog";

import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";

import { NgxSimpleTextEditorModule } from 'ngx-simple-text-editor';
//import { QuillModule } from "ngx-quill";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomSidenavComponent } from './components/custom-sidenav/custom-sidenav.component';
import { HomeComponent } from './components/pages/home/home.component';
import { CalendarComponent } from './components/pages/calendar/calendar.component';
// import { CustomSidenavComponent } from './components/custom-sidenav/custom-sidenav.component';
// import { HomeComponent } from './components/pages/home/home.component';
// import { CalendarComponent } from './components/pages/calendar/calendar.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomSidenavComponent,
    HomeComponent,
    CalendarComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
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
    FontAwesomeModule
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

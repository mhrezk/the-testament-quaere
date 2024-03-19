import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { RouterModule, RouterOutlet } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { NgOptimizedImage } from "@angular/common";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";

import { NgxSimpleTextEditorModule } from 'ngx-simple-text-editor';
import { QuillModule } from "ngx-quill";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomSidenavComponent } from './components/custom/custom-sidenav/custom-sidenav.component';
import { HomeComponent } from './components/pages/home/home.component';
import { CalendarComponent } from './components/pages/calendar/calendar.component';
import { CustomHeaderComponent } from './components/custom/custom-header/custom-header.component';

// import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    CustomSidenavComponent,
    HomeComponent,
    CalendarComponent,
    CustomHeaderComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    RouterOutlet,
    HttpClientModule,
    NgOptimizedImage,
    RouterModule,
    BrowserAnimationsModule,
    NgxSimpleTextEditorModule,
    QuillModule.forRoot(),
    // NgbModule,
    FontAwesomeModule
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

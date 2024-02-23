import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { RouterModule, RouterOutlet } from "@angular/router";
import { NgOptimizedImage } from "@angular/common";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatListModule } from "@angular/material/list";

import { NgxSimpleTextEditorModule } from 'ngx-simple-text-editor';
import { QuillModule } from "ngx-quill";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomSidenavComponent } from './components/custom-sidenav/custom-sidenav.component';
import { HomeComponent } from './components/pages/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomSidenavComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterOutlet,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    NgOptimizedImage,
    RouterModule,
    NgxSimpleTextEditorModule,
    QuillModule.forRoot(),
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {NgxSimpleTextEditorModule} from 'ngx-simple-text-editor';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxSimpleTextEditorModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

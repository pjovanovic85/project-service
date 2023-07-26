import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { ShellComponent } from './shell/shell.component';
import { HttpClientModule } from '@angular/common/http';

const components = [
  AppComponent,
  ShellComponent,
];

const modules = [
  BrowserModule,
  AppRoutingModule,
  BrowserAnimationsModule,
  MaterialModule,
  HttpClientModule,
];

@NgModule({
  declarations: [...components],
  imports: [...modules],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

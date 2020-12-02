import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TitleComponent } from './components/title/title.component';
import { GuestListComponent } from './components/guest-list/guest-list.component';
import { GuestListItemComponent } from './components/guest-list-item/guest-list-item.component';

@NgModule({
  declarations: [
    AppComponent,
    TitleComponent,
    GuestListComponent,
    GuestListItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  //exports: [AppComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

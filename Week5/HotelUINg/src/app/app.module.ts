import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpHeaders, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TitleComponent } from './components/title/title.component';
import { GuestListComponent } from './components/guest-list/guest-list.component';
import { GuestListItemComponent } from './components/guest-list-item/guest-list-item.component';
import { GuestFilterPipe } from './pipes/guest-filter.pipe';
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { RoomListComponent } from './components/room-list/room-list.component';
import { RoomFilterPipe } from './pipes/room-filter.pipe';
import { RoomListItemComponent } from './components/room-list-item/room-list-item.component';

@NgModule({
  declarations: [
    AppComponent,
    TitleComponent,
    GuestListComponent,
    GuestListItemComponent,
    GuestFilterPipe,
    SearchBarComponent,
    LandingPageComponent,
    RoomListComponent,
    RoomFilterPipe,
    RoomListItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  //exports: [AppComponent],
  providers: [HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }

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
import { RoomDetailsComponent } from './components/room-details/room-details.component';
import { RoomDisplayComponent } from './components/room-display/room-display.component';

@NgModule({
  declarations: [
    AppComponent,
    TitleComponent,
    GuestListComponent,
    GuestListItemComponent,
    GuestFilterPipe,
    SearchBarComponent,
    LandingPageComponent,
    RoomDetailsComponent,
   RoomDisplayComponent
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

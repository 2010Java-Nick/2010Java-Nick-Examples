import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GuestListComponent } from './components/guest-list/guest-list.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { RoomListComponent } from './components/room-list/room-list.component';

const routes: Routes = [
  {path: 'guests', component: GuestListComponent},
  {path: 'home', component: LandingPageComponent},
  {path: 'rooms', component: RoomListComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookingListComponent } from './components/booking-list/booking-list.component';
import { GuestListComponent } from './components/guest-list/guest-list.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';

const routes: Routes = [
  {path: 'guests', component: GuestListComponent},
  {path: 'home', component: LandingPageComponent},
  {path: 'bookings', component: BookingListComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

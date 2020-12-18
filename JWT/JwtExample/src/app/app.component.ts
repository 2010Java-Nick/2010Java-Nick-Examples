import { Component, OnInit } from '@angular/core';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ LoginService ]
})
export class AppComponent implements OnInit {
  title = 'app';
  loggedIn = false;

  ngOnInit() {
    this.loginService.isLoggedIn.subscribe(
      (loggedIn) => {
        this.setLoggedIn(loggedIn);
        console.log(`inside subscribed getCheckLogin ` + loggedIn);
      } );
  }

  constructor (private loginService: LoginService) {
  }

  public setLoggedIn(loggedIn: boolean): void {
    this.loggedIn = loggedIn;
  }

  onAttempt(input: string) {
    if (input === `success`) {
     this.loggedIn = true;
   } else { this.loggedIn = false; }
  }
}

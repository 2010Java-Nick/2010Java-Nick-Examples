import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { HttpErrorResponse } from '../../../../node_modules/@angular/common/http';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  errorMessage: string;

  @Output() loginAttempt = new EventEmitter<string>();

  private authHeader = environment.authHeader;

  constructor(private loginService: LoginService) { }

  ngOnInit() {
  }

  public submit(): void {

    this.loginService.login(this.username, this.password).subscribe(
      (resp) => {
        this.errorMessage = `successful login`;
        this.loginService.setJWT(resp.headers.get(this.authHeader));
        console.log(this.authHeader + ` : ` + resp.headers.get(this.authHeader));
        console.log(`Expires: ` + resp.headers.get(`Expires`));
        this.loginService.setExpires(Number(resp.headers.get(`Expires`)));
        console.log(resp.headers);
        console.log('JWT set to ' + this.loginService.getJWT(new Date().getTime()));
        this.loginAttempt.emit(`success`);
      },
      (error) => {
        this.handleError(error);
        this.loginAttempt.emit(`failed`);
      }
    );

  }

  private handleError(error: HttpErrorResponse) {
    console.log(error);
    if (error.status === 404) {
      this.errorMessage = `Sorry, could not find Login`;
    } else if (error.status === 403) {
      this.errorMessage = 'Login credentials provided are inaccurate';
    } else {
      this.errorMessage = `problem with service, sorry try again`;
    }

  }

}

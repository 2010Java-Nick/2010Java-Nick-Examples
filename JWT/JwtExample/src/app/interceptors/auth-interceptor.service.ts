import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent } from '../../../node_modules/@angular/common/http';
import { LoginService } from '../services/login.service';
import { Observable } from '../../../node_modules/rxjs';
import { environment } from '../../environments/environment';

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {

  private jWT: string;

  constructor(private loginService: LoginService) {
    this.loginService.jWTChange.subscribe((jWT) => {
      this.jWT = jWT;
      console.log(`auth-interceptor has recived the jWT change as ` + jWT);
    } );
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const authHeader = environment.authHeader;
    const authorizedReq =
    req.clone({
      headers: req.headers.set( authHeader, this.loginService.getJWT(new Date().getTime()) )
    });
    return next.handle(authorizedReq);
  }

}

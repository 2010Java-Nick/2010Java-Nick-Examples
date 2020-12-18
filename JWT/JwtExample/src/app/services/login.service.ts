import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { User } from '../domains/User';
import { Observable, Subject, BehaviorSubject } from '../../../node_modules/rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly loginUrl = environment.loginUrl;

  private jWT = this.retrieveLocalStore();

  private expires: number;

  isLoggedIn: Subject<boolean> = new BehaviorSubject<boolean>(this.checkLocalStore());
  jWTChange: Subject<string> = new BehaviorSubject<string>(this.jWT);

  constructor(private httpClient: HttpClient) {
  }

/*   public getCheckLoggedIn(): Observable<boolean> {
    return this.isLoggedIn.asObservable();
  }

  public getCheckJWT(): Observable<string> {
    return this.jWTChange.asObservable();
  } */

  private retrieveLocalStore(): string {
    return (localStorage.getItem(`auth`) != null) ? localStorage.getItem(`auth`) : '';
  }

  private checkLocalStore() {
    if (this.jWT !== '') {
      return true;
    } else { return false; }
  }

  public login(username: string, password: string): Observable<HttpResponse<string>> {

    const user = new User(username, password);

    return this.httpClient.post<string>(this.loginUrl, user, { observe: 'response' });

  }

  public setJWT(jWT: string): void {
    this.jWT = jWT;
    console.log(`changed jWT in login service to ` + jWT);
    this.jWTChange.next(jWT);
    localStorage.setItem('auth', jWT);
    if (jWT !== '') {
      console.log(`inside setJWT and logged in correctly`);
      this.isLoggedIn.next(true);
    } else { this.isLoggedIn.next(false); }
  }

  public setExpires(expires: number): void {
    this.expires = expires;
    localStorage.setItem('expires', expires.toString());
  }

  public getJWT(time: number): string {
    if (time > this.expires) {
      this.setJWT(``);
      this.isLoggedIn.next(false);
    }
    return this.jWT;
  }

}

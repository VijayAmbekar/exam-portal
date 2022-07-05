import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  TOKEN = "TOKEN";

  public loginStatusSubject = new Subject<boolean>();

  constructor(private http: HttpClient) { }

  /**
   * generate JWT token from user
   */
  generateToken(loginData: any) {
    return this.http.post(`${baseUrl}/generateToken`, loginData);
  }

  /**
   * 
   * @returns Get currently logged in user data
   */
  public getCurrentUser() {
    return this.http.get(`${baseUrl}/currentUser`);
  }

  /**
   * Login user - set token in localStorage
   */
  public loginUser(token: string) {
    localStorage.setItem(this.TOKEN, token);
  }

  /**
   * Is user logged in
   */
  public isLoggedIn() {
    
    let token = localStorage.getItem(this.TOKEN);

    return undefined!=token && ''!=token && null!=token;
  }

  /**
   * remove JWT token from localstorage
   */
  public logout() {
    localStorage.removeItem(this.TOKEN);
    localStorage.removeItem('USER');    
  }

  /**
   * 
   * @returns Get token
   */
  public getToken() {
    return localStorage.getItem(this.TOKEN);
  }

  public setUserDetails(user: any) {
    localStorage.setItem('USER', JSON.stringify(user));
  }

  public getUser() {
    let usr = localStorage.getItem('USER');

    if(null != usr) {
      return JSON.parse(usr);
    } else {
      this.logout();
      return null;
    }
  }

  /**
   * 
   * @returns This is based on assumption that User has a single role
   */
  public getUserRole() {
    let user = this.getUser();

    if(null != user) {
      return user.authorities[0].authority;
    }
  }

}

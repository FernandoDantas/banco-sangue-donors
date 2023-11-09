import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment'
import { Users } from '../core/model';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiURL: string = environment.apiUrlBase + "/v1/users";
  oauthTokenUrl: string = environment.apiUrlBase + environment.tokenUrl;
  jwtPayload: any;
  clientId: string = environment.clientId;
  clientSecret: string = environment.clientSecret;

  constructor(
    private http: HttpClient,
    private jwtHelper: JwtHelperService
  ) {
    this.loadToken();
  }

  add(user: Users) : Observable<Users>{
    return this.http.post<Users>(this.apiURL, user);
  }

  tryToLogin(userEmail: string, cryptedPass: string): Observable<void> {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/x-www-form-urlencoded')
      .append('Authorization', 'Basic '+ btoa(`${this.clientId}:${this.clientSecret}`));

    const body = `username=${userEmail}&password=${cryptedPass}&grant_type=password`;

    return this.http.post<void>(this.oauthTokenUrl, body, {
      headers,
      withCredentials: false,
    });
  }

  isAuthenticated() : boolean {
    const token = localStorage.getItem('access_token');
    if(token){
      const expired = this.jwtHelper.isTokenExpired(token);
      return !expired;
    }
    return false;
  }

  getNewAccessToken(): Observable<void>{
    const headers = new HttpHeaders()
     .append('Content-Type', 'application/x-www-form-urlencoded')
     .append('Authorization', 'Basic '+ btoa(`${this.clientId}:${this.clientSecret}`));

     const body = 'grant_type=refresh_token';

     return this.http.post<void>(this.oauthTokenUrl, body, {
      headers,
      withCredentials: false,
    })
  }

  clearAccessToken(){
    localStorage.removeItem('access_token');
    this.jwtPayload = null;
  }

  isAccessTokenInvalid(){
    const token = localStorage.getItem('access_token');

    return !token || this.jwtHelper.isTokenExpired(token);
  }


  havePermission(permission: string){
    return this.jwtPayload && this.jwtPayload.authorities.includes(permission);
  }

  haveAnyPermission(roles: any){
    for (const role of roles){
      if(this.havePermission(role)){
        return true;
      }
    }
    return false;
  }

  public storageToken(token: string){
    this.jwtPayload = this.jwtHelper.decodeToken(token);
    localStorage.setItem('access_token', token);
  }

public loadToken(){
  const token = localStorage.getItem('access_token');

  if(token){
    this.storageToken(token);
  }
}

}

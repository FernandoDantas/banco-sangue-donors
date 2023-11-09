import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  tokensRevokeUrl: string;

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) {
    this.tokensRevokeUrl = `${environment.apiUrlBase}/v1/tokens/revoke`;
  }

  logout(){
    return this.http.delete(this.tokensRevokeUrl, { withCredentials: false })
      .toPromise()
      .then(( )=> {
        this.auth.clearAccessToken();
      })
  }
}

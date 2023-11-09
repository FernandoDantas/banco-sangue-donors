import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';;
import { environment } from './../../../environments/environment';
import { Users } from './../../core/model';
import { AuthService } from './../auth.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  msgSuccess?: string;
  signingup?: boolean;
  erro?: string;

  form: FormGroup;

  tokenUrl: string = environment.apiUrlBase + environment.tokenUrl;

  constructor(
    private router: Router,
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) { }

 ngOnInit() {
  this.form = this.formBuilder.group({
    userEmail: [null, [Validators.required, Validators.email]],
    userName: this.signingup ? [null, [Validators.required, Validators.minLength(3)]] : '',
    cryptedPass: [null, [Validators.required, Validators.minLength(6)]],
  })
 }

 onSubmit(){
    this.authService.tryToLogin(this.form.value.userEmail,
      this.form.value.cryptedPass)
    .subscribe({
      next: (response: any) => {
        this.authService.storageToken(response.access_token)
        this.router.navigate(['home']);
      },
      error: (response: any) => {
        this.msgSuccess = "";
        if (response.status === 400) {
          if (response.error.error === 'invalid_grant') {
            this.erro = response.error.error_description;
            this.form.value.cryptedPass = "";
          }
        }
      }
    });

 }

 prepareRegister(event: { preventDefault: () => void; }){
  event.preventDefault();
  this.signingup = true;
  this.form.reset();
 }

 cancelRegistration(){
  this.signingup = false;
  this.erro = "";
  this.msgSuccess = "";
  this.form.reset();
 }

 add(){
    const user: Users = new Users();
    user.userEmail = this.form.value.userEmail;
    user.userName = this.form.value.userName;
    user.cryptedPass = this.form.value.cryptedPass;

    this.authService
      .add(user)
      .subscribe({
        next: (response) => {
          this.erro = "";
          this.cancelRegistration();
          this.msgSuccess = "Cadastro realizado com sucesso! Efetue o login.";
          this.form.reset();
        },
        error: (response) => {
          this.msgSuccess = "";
          this.erro = response.error.detail;
        }
      })
 }

 aplyCssError(field: any){
  return {
    'is-invalid' : this.verifyValidTouched(field),
    'is-valid' : this.verifyValidTouched(field),
  }
 }

 verifyValidTouched(field: any){
  return !this.form.get(field)?.valid && this.form.get(field)?.touched;
 }

 verifyEmailValid(){
  let fieldEmail = this.form.get('userEmail');
    if(fieldEmail?.errors){
      return fieldEmail.errors['userEmail'] && fieldEmail.touched;
    }
 }

}

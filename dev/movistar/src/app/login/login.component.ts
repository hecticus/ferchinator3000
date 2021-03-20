import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/services/login/login.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
    public user = '';
    public pass = '';
    public error = false;
    constructor(private loginService: LoginService, private router: Router) {}

    ngOnInit(): void {}

    public onLogin(): any {
        const logged = this.loginService.login({
            user: this.user,
            password: this.pass,
        });

        if (logged) {
            this.router.navigate(['calcular']);
        } else {
          this.error = true;
        }
    }
}

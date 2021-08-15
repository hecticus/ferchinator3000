import { Injectable } from '@angular/core';
import { Login } from './dto/login';
import { StorageGateway } from './storage.gateway';

@Injectable({
    providedIn: 'root',
})
export class LoginService {
    constructor(private storageGateway: StorageGateway) {}

    public login(login: Login): any {
        if (login.user === 'admin' && login.password === 'ferchinator2021') {
            this.storageGateway.put('user', JSON.stringify(login));
            return true;
        }

        return false;
    }

    public isLogged(): boolean {
        return this.storageGateway.get('user') !== null;
    }

    public logout(): any {
        this.storageGateway.delete('user');
    }
}

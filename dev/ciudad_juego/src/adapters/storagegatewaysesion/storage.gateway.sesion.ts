import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';
import { PromoGateway } from 'src/services/promocion/promo.gateway';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from 'src/environments/environment';
import { StorageGateway } from 'src/services/login/storage.gateway';

@Injectable()
export abstract class StorageGatewaySession implements StorageGateway {
    constructor() {}

    public put(name: string, value: any): any {
        sessionStorage.setItem(name, value);
    }
    public get(name: string): any {
        return sessionStorage.getItem(name);
    }
    public delete(name: string): any {
        sessionStorage.removeItem(name);
    }
}

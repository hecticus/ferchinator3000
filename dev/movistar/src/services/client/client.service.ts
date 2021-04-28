import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';
import { ClientGateway } from './client.gateway';

@Injectable({
    providedIn: 'root',
})
export class ClientService {
    constructor(private clientGateway: ClientGateway) {}
    public updateClients(promoId: number): Observable<Resultado[]> {
        return this.clientGateway.updateClients(promoId);
    }

}

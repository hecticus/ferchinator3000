import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from 'src/environments/environment';
import { ClientGateway } from 'src/services/client/client.gateway';

@Injectable()
export abstract class ClientGatewayWeb implements ClientGateway {
    constructor(private http: HttpClient) {}

    public updateClients(promoId: number): Observable<Resultado[]> {
        return this.http.get<Resultado[]>(`${SERVER_URL}/client/update/${promoId}`);
    }
}

// this.http
//     .get<NoticiasRemotoEntity>(`${SERVER_URL}${this.serviceRoute}/hoy`)
//     .pipe(map((result) => result.noticias.map(this.mapper.mapFrom)))
//     .subscribe((noticias) => {
//         this.noticias = noticias.reverse();
//         observer.next(noticias);
//         observer.complete();
//     }, observer.error.bind(observer));

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';
import { PromoGateway } from 'src/services/promocion/promo.gateway';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from 'src/environments/environment';

@Injectable()
export abstract class PromoGatewayWeb implements PromoGateway {
    constructor(private http: HttpClient) {}

    public getPromocionResult(promoId: number): Observable<Resultado[]> {
        return this.http.get<Resultado[]>(`${SERVER_URL}/promocion/${promoId}`);
    }

    public calcutaleResult(promoId: number): Observable<Resultado[]> {
        return this.http.get<Resultado[]>(`${SERVER_URL}/promocion/${promoId}/calcular`);
    }

    public setPromocionResult(promoId: number, resultado: Resultado[]): any {
        return this.http.post<Resultado[]>(`${SERVER_URL}/promocion/${promoId}`, resultado);
    }
}

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';
import { PromoGateway } from './promo.gateway';

@Injectable({
    providedIn: 'root',
})
export class PromoService {
    constructor(private promoGateway: PromoGateway) {}
    public getResultadoByPromoId(promoId: number): Observable<Resultado[]> {
        return this.promoGateway.getPromocionResult(promoId);
    }

    public setResultadoByPromoId(
        promoId: number,
        resultado: Resultado[]
    ): Observable<any> {
        return this.promoGateway.setPromocionResult(promoId, resultado);
    }

    public getCalcPromotion(promoId: number): Observable<Resultado[]> {
        return this.promoGateway.calcutaleResult(promoId);
    }
}

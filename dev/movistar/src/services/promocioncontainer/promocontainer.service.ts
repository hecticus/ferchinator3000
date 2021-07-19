import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PromoContainer } from 'src/domain/promocontainer';
import { Resultado } from 'src/domain/resultado';
import { PromoContainerGateway } from './promocontainer.gateway';

@Injectable({
    providedIn: 'root',
})
export class PromoContainerService {
    constructor(private promoContainerGateway: PromoContainerGateway) {}
    public getResultadoByPromoId(promoId: number): Observable<PromoContainer> {
        return this.promoContainerGateway.getPromocionContainer(promoId);
    }

}

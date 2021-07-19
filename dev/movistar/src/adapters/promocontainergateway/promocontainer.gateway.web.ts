import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from 'src/environments/environment';
import { PromoContainerGateway } from 'src/services/promocioncontainer/promocontainer.gateway';
import { PromoContainer } from 'src/domain/promocontainer';
import { map } from 'rxjs/operators';
import { Promo } from 'src/domain/promo';

@Injectable()
export abstract class PromoContainerGatewayWeb implements PromoContainerGateway {
    constructor(private http: HttpClient) {}

    public getPromocionContainer(promoId: number): Observable<PromoContainer> {
        return this.http.get<PromoContainer>(`${SERVER_URL}/promo-container/${promoId}`).pipe(
            map((promoContainer: PromoContainer) => {
                promoContainer.promociones.forEach((promocion: Promo) => {
                    if (promocion.premiacion !== '') {
                        promocion.resultados = JSON.parse(promocion.premiacion);
                    }
                });
                return promoContainer;
            })
        );
    }
}

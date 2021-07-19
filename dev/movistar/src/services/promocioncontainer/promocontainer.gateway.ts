import { Observable } from 'rxjs';
import { PromoContainer } from 'src/domain/promocontainer';

export abstract class PromoContainerGateway {
  public abstract getPromocionContainer(promoId: number): Observable<PromoContainer>;
}

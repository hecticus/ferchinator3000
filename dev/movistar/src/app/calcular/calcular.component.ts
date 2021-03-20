import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';
import { environment } from 'src/environments/environment';
import { PromoService } from 'src/services/promocion/promo.service';

@Component({
    selector: 'app-calcular',
    templateUrl: './calcular.component.html',
    styleUrls: ['./calcular.component.scss'],
})
export class CalcularComponent implements OnInit {
    public promo: Resultado[] = [];
    public cardinales = ['primer' , 'segundo', 'tercer', 'cuarto', 'quinto', 'sexto', 'septimo', 'octavo'];

    constructor(private promoservice: PromoService) {}

    ngOnInit(): void {
        this.promoservice
            .getResultadoByPromoId(environment.promo)
            .subscribe((response) => (this.promo = response));
    }

    public generate(): void {
        this.promoservice
            .getCalcPromotion(environment.promo)
            .subscribe((response) => (this.promo = response));
    }

    public guardar(): void {
        this.promoservice
            .setResultadoByPromoId(environment.promo, this.promo)
            .subscribe();
    }
}

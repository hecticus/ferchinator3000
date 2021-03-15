import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';
import { environment } from 'src/environments/environment';
import { PromoService } from 'src/services/promocion/promo.service';

@Component({
    selector: 'app-resultado',
    templateUrl: './resultado.component.html',
    styleUrls: ['./resultado.component.sass'],
})
export class ResultadoComponent implements OnInit {
    public promo$: Observable<Resultado[]> | null = null;

    constructor(private promoservice: PromoService) {}

    ngOnInit(): void {
        this.promo$ = this.promoservice.getResultadoByPromoId(
            environment.promo
        );

        this.promo$.subscribe();
    }
}

import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { PromoContainer } from 'src/domain/promocontainer';
import { environment } from 'src/environments/environment';
import { PromoContainerService } from 'src/services/promocioncontainer/promocontainer.service';

@Component({
    selector: 'app-resultado',
    templateUrl: './resultado.component.html',
    styleUrls: ['./resultado.component.scss'],
})
export class ResultadoComponent implements OnInit {
    public promo: Observable<PromoContainer> | null = null;

    constructor(private promoContainerService: PromoContainerService) {}

    ngOnInit(): void {
        this.promo = this.promoContainerService.getResultadoByPromoId(
            environment.contenedor
        );

        this.promo.subscribe();
    }
}

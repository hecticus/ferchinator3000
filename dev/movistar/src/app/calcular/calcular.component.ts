import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';
import { environment } from 'src/environments/environment';
import { ClientService } from 'src/services/client/client.service';
import { PromoService } from 'src/services/promocion/promo.service';

@Component({
    selector: 'app-calcular',
    templateUrl: './calcular.component.html',
    styleUrls: ['./calcular.component.scss'],
})
export class CalcularComponent implements OnInit {
    public promo: Resultado[] = [];
    public promoEnded = false;
    public ready = false;
    public cardinales = ['primer', 'segundo', 'tercer', 'cuarto', 'quinto', 'sexto', 'septimo', 'octavo'];

    constructor(private promoservice: PromoService, private clientService: ClientService) {}

    ngOnInit(): void {
        this.clientService.updateClients(environment.promo).subscribe((resp) => {
            this.promoservice.getResultadoByPromoId(environment.promo).subscribe((response) => {
                this.promo = response;
                this.ready = true;
                if (this.promo && this.promo.length > 0) {
                    this.promoEnded = true;
                }
            });
        });
    }

    public generate(): void {
        this.promoservice.getResultadoByPromoId(environment.promo).subscribe((response) => {
            if (response && response.length > 0) {
                alert('ya hay datos guardados, estos solo modificaran si actualiza y acepta la condicion de actualizar');
            }

            this.promoservice.getCalcPromotion(environment.promo).subscribe((response2) => (this.promo = response2));
        });
    }

    public guardar(): void {
        // let calc = true;
        this.promoservice.getResultadoByPromoId(environment.promo).subscribe((response) => {
            if (confirm('Esta accion no puede ser modificada, esta seguro de que quiere seguir adelante?')) {
                this.promoservice.setResultadoByPromoId(environment.promo, this.promo).subscribe();
                this.promoEnded = true;
            } else {
                this.promoservice.getResultadoByPromoId(environment.promo).subscribe((responsae) => {
                    this.promo = responsae;
                    if (this.promo && this.promo.length > 0) {
                        this.promoEnded = true;
                    }
                });
            }
        });
    }
}

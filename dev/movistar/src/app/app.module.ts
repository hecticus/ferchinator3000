import { HttpClient, HttpClientModule } from '@angular/common/http';
import { forwardRef, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { PromoGatewayWeb } from 'src/adapters/promogateway/promo.gateway.web';
import { PromoGateway } from 'src/services/promocion/promo.gateway';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ResultadoComponent } from './resultado/resultado.component';
import { CalcularComponent } from './calcular/calcular.component';

@NgModule({
    declarations: [AppComponent, ResultadoComponent, CalcularComponent],
    imports: [BrowserModule, AppRoutingModule, HttpClientModule],
    providers: [
        {
            provide: PromoGateway,
            useClass: forwardRef(() => PromoGatewayWeb),
        },
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}

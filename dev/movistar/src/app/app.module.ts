import { HttpClient, HttpClientModule } from '@angular/common/http';
import { forwardRef, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { PromoGatewayWeb } from 'src/adapters/promogateway/promo.gateway.web';
import { PromoGateway } from 'src/services/promocion/promo.gateway';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ResultadoComponent } from './resultado/resultado.component';
import { CalcularComponent } from './calcular/calcular.component';
import { StorageGateway } from 'src/services/login/storage.gateway';
import { StorageGatewaySession } from 'src/adapters/storagegatewaysesion/storage.gateway.sesion';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@NgModule({
    declarations: [AppComponent, ResultadoComponent, CalcularComponent, LoginComponent],
    imports: [FormsModule, BrowserModule, AppRoutingModule, HttpClientModule, RouterModule],
    providers: [
        {
            provide: PromoGateway,
            useClass: forwardRef(() => PromoGatewayWeb),
        },
        {
            provide: StorageGateway,
            useClass: forwardRef(() => StorageGatewaySession),
        },
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}

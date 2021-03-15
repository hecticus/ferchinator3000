import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalcularComponent } from './calcular/calcular.component';
import { ResultadoComponent } from './resultado/resultado.component';

const routes: Routes = [
    { path: '', component: ResultadoComponent },
    { path: 'calcular', component: CalcularComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}

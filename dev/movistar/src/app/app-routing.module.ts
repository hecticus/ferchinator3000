import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalcularComponent } from './calcular/calcular.component';
import { LoginGuard } from './guards/login.guard';
import { LoginComponent } from './login/login.component';
import { ResultadoComponent } from './resultado/resultado.component';

const routes: Routes = [
    { path: '', component: ResultadoComponent },
    { path: 'calcular', component: CalcularComponent, canActivate: [LoginGuard] },
    { path: 'login', component: LoginComponent, },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}

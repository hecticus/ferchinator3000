import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';

export abstract class ClientGateway {
  public abstract updateClients(promoId: number): Observable<Resultado[]>;

}

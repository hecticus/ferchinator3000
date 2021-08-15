import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';

export abstract class PromoGateway {
  public abstract getPromocionResult(promoId: number): Observable<Resultado[]>;
  public abstract calcutaleResult(promoId: number): Observable<Resultado[]>;
  public abstract setPromocionResult(promoId: number, resultado: Resultado[]): any;
}

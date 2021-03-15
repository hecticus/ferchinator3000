export interface Resultado {
    premio: Premio;
    seleccionados: Seleccionado[];
}

export interface Seleccionado {
    id: number;
    msisdn: string;
    promoId: string;
    carrier: string;
    country: string;
}

export interface Premio {
    id: number;
    promoId: number;
    nombre: string;
    orden: number;
    suplente: number;
}

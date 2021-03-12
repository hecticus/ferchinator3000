package models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Premio extends BaseModel {
    @Column()
    private int promoId;
    @Column()
    private String nombre;
    @Column()
    private int orden;
    @Column()
    private int suplente;

    public int getPromoId() {
        return promoId;
    }

    public void setPromoId(int promoId) {
        this.promoId = promoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getSuplente() {
        return suplente;
    }

    public void setSuplente(int suplente) {
        this.suplente = suplente;
    }
}
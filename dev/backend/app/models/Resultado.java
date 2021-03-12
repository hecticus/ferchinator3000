package models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Resultado extends BaseModel {
    @Column()
    private int promoId;
    @Column()
    private String fecha;
    @Column()
    private String resultado;

    public int getPromoId() {
        return promoId;
    }

    public void setPromoId(int promoId) {
        this.promoId = promoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}

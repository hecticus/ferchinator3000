package models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Configuracion extends BaseModel {
    @Column()
    private String llave;
    @Column()
    private String valor;


    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
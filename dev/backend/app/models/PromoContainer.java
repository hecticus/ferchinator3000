package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PromoContainer extends BaseModel {
    @Column()
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Promocion> promociones = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }
}
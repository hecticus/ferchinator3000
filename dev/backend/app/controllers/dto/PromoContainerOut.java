package controllers.dto;


import java.util.ArrayList;
import java.util.List;

public class PromoContainerOut {
    private Integer id;
    private String nombre;
    private List<PromocionOut> promociones = new ArrayList<>();

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PromocionOut> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<PromocionOut> promociones) {
        this.promociones = promociones;
    }
}
package controllers.dto;

import com.google.gson.annotations.Expose;
import models.PromoContainer;
import models.Promocion;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

public class PromocionOut {
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private int activa;
    private String operadoraId;
    private String businessID;
    private String premiacion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getActiva() {
        return activa;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }

    public String getOperadoraId() {
        return operadoraId;
    }

    public void setOperadoraId(String operadoraId) {
        this.operadoraId = operadoraId;
    }

    public String getBusinessID() {
        return businessID;
    }

    public void setBusinessID(String businessID) {
        this.businessID = businessID;
    }

    public String getPremiacion() {
        return premiacion;
    }

    public void setPremiacion(String premiacion) {
        this.premiacion = premiacion;
    }
}
package applicationservices.premiacionesservice;


import com.google.gson.Gson;
import models.Resultado;

public class ResultadoEntity {
    public int promoId;
    public String fecha;
    public String resultado;

    public ResultadoEntity(Resultado resultado) {
        promoId = resultado.getPromoId();
        fecha = resultado.getFecha();
        this.resultado = resultado.getResultado();
    }

    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

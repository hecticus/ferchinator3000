package controllers;

import applicationservices.premiacionesservice.PremiacionesService;
import applicationservices.premiacionesservice.dto.Resultado;
import com.fasterxml.jackson.databind.JsonNode;
import models.Promocion;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Map;


public class PremiacionController  extends Controller {

    @Inject
    private PremiacionesService premiacionesService;

    public Result index(int promocion) {
        Resultado res = premiacionesService.CalcularResultado(promocion);
        return ok(res.resultado).as("application/json");
    }

    public Result get(int promocion) {
        Promocion res = premiacionesService.getResultadoByPromoId(promocion);
        return ok(res.getPremiacion() ==null? "[]": res.getPremiacion()).as("application/json");
    }

    public Result update(Http.Request request, int promocion) {
        String aux = request.body().asJson().toString();
        Promocion promo = premiacionesService.crearPremiacionEnPromo(promocion, aux);
        return ok();
    }
}

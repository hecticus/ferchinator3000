package controllers;

import applicationservices.premiacionesservice.PremiacionesService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import models.Resultado;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class PremiacionController  extends Controller {

    @Inject
    private PremiacionesService premiacionesService;

    public Result index(int promocion) {
        Resultado res = premiacionesService.CalcularResultado(promocion);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.valueToTree(res);
        return ok(res.getResultado()).as("application/json");
    }

    public Result get(int promocion) {
        Resultado res = premiacionesService.getResultadoByPromoId(promocion);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.valueToTree(res);
        return ok(res.getResultado()).as("application/json");
    }
}

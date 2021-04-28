package controllers;

import applicationservices.clientservice.ClientService;
import applicationservices.clientservice.dto.ClientList;
import applicationservices.premiacionesservice.PremiacionesService;
import applicationservices.premiacionesservice.dto.Resultado;
import models.Promocion;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;


public class ClientController extends Controller {

    @Inject
    private ClientService clientService;

    public Result update(String promoId) {
        ClientList res = clientService.GetExternalClients(promoId);
        return ok().as("application/json");
    }
}

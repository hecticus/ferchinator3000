package controllers;

import applicationservices.promocontainerservice.PromoContainerService;
import com.google.gson.Gson;
import controllers.mapper.PromoContainerToPromoContainerOut;
import models.PromoContainer;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class PromoContainerController extends Controller {

    @Inject
    private PromoContainerService promoContainerService;

    @Inject
    private PromoContainerToPromoContainerOut promoContainerToPromoContainerOut;

    @Inject
    Gson gson;

    public Result get(int promoContainerId) throws Exception {
        PromoContainer res = promoContainerService.GetPromoContainerById(promoContainerId);
        return ok(gson.toJson(promoContainerToPromoContainerOut.mapTo(res))).as("application/json");
    }
}

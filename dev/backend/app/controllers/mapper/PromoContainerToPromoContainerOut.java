package controllers.mapper;

import controllers.dto.PromoContainerOut;
import controllers.dto.PromocionOut;
import models.PromoContainer;
import models.Promocion;

import java.util.ArrayList;
import java.util.List;

public class PromoContainerToPromoContainerOut {

    public PromoContainerOut mapTo(PromoContainer promoContainer) {
        PromoContainerOut promoContainerOut = new PromoContainerOut();
        promoContainerOut.setId(promoContainer.getId());
        promoContainerOut.setNombre(promoContainer.getNombre());

        List<PromocionOut> promocionOuts = new ArrayList<>();
        for(Promocion promocion: promoContainer.getPromociones()) {
            PromocionOut promocionOut = new PromocionOut();
            promocionOut.setActiva(promocion.getActiva());
            promocionOut.setBusinessID(promocion.getBusinessID());
            promocionOut.setFechaFin(promocion.getFechaFin());
            promocionOut.setFechaInicio(promocion.getFechaInicio());
            promocionOut.setNombre(promocion.getNombre());
            promocionOut.setOperadoraId(promocion.getOperadoraId());
            promocionOut.setPremiacion(promocion.getPremiacion() == null? "": promocion.getPremiacion());

            promocionOuts.add(promocionOut);
        }

        promoContainerOut.setPromociones(promocionOuts);
        return promoContainerOut;
    }
}

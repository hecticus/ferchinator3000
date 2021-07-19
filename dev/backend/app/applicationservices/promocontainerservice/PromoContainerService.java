package applicationservices.promocontainerservice;

import applicationservices.premiacionesservice.dto.PremioResultadoDto;
import applicationservices.premiacionesservice.dto.Resultado;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import interfaceadapters.premiorepositorydao.*;
import models.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class PromoContainerService {

    @Inject
    private PromoContainerRepositoryDao promoContainerRepositoryDao;

    public PromoContainer GetPromoContainerById(int promocionContainerId) throws Exception {
        return promoContainerRepositoryDao.findById(promocionContainerId);
    }
}

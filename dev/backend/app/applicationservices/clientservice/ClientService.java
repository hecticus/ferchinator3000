package applicationservices.clientservice;

import applicationservices.clientservice.dto.ClientList;
import applicationservices.clientservice.dto.ClientWebEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import interfaceadapters.premiorepositorydao.ConfiguracionRepositoryDao;
import interfaceadapters.premiorepositorydao.PromoRepositoryDao;
import io.ebean.Ebean;
import io.ebean.SqlUpdate;
import models.Client;
import models.Promocion;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;

@Singleton
public class ClientService {

    @Inject
    private WSClient ws;
    @Inject
    private ConfiguracionRepositoryDao configuracionRepositoryDao;
    @Inject
    private PromoRepositoryDao promoRepositoryDao;
    private final String url = "http://api.hecticus.com/ferchinator2/6/";
    @Inject
    Gson gson = new Gson();

    public ClientList GetExternalClients(String promoId) throws Exception {

        Promocion promocion = promoRepositoryDao.findById(Integer.parseInt(promoId));

        String fechaIni = promocion.getFechaInicio();
        String fechaFin = promocion.getFechaFin();

        CompletionStage<JsonNode> jsonPromise2 = ws.url(url+ promocion.getOperadoraId() + "/10/"  + fechaIni + "/" + fechaFin).get()
                .thenApply(WSResponse::asJson);
        JsonNode jsonr = Json.newObject();
        ClientList list = null;
        SqlUpdate down = Ebean.createSqlUpdate("DELETE FROM client WHERE id > 0");
        down.execute();
        try {
            jsonr = jsonPromise2.toCompletableFuture().get();
            list = gson.fromJson(jsonr.get("response").toString(), ClientList.class);
            for (ClientWebEntity clientEntity : list.clients) {
                Client client = new Client();
                client.setCarrier(Long.toString(clientEntity.carrier));
                client.setCountry(Long.toString(clientEntity.country));
                client.setMsisdn(clientEntity.msisdn);
                client.setPromoId(promoId);
                client.insert();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getStackTrace());
        }
        return list;
    }
}

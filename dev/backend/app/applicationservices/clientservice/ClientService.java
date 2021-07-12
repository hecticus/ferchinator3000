package applicationservices.clientservice;

import applicationservices.clientservice.dto.ClientList;
import applicationservices.clientservice.dto.ClientWebEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import interfaceadapters.premiorepositorydao.ConfiguracionRepositoryDao;
import io.ebean.Ebean;
import io.ebean.SqlUpdate;
import models.Client;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;

@Singleton
public class ClientService {

    private final WSClient ws;
    private ConfiguracionRepositoryDao configuracionRepositoryDao;
    private final String url = "http://api.hecticus.com/ferchinator2/6/9/10/";
    Gson gson = new Gson();

    @Inject
    public ClientService(WSClient ws, ConfiguracionRepositoryDao configuracionRepositoryDao) {
        this.configuracionRepositoryDao = configuracionRepositoryDao;
        this.ws = ws;
    }

    public ClientList GetExternalClients(String promoId) throws Exception {

        String fechaIni = configuracionRepositoryDao.obtenerPorLlave("fecha_ini");
        String fechaFin = configuracionRepositoryDao.obtenerPorLlave("fecha_fin");

        CompletionStage<JsonNode> jsonPromise2 = ws.url(url + fechaIni + "/" + fechaFin).get()
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

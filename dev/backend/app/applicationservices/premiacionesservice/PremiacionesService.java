package applicationservices.premiacionesservice;

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
public class PremiacionesService {

    @Inject
    private ClienteRepositoryDao clienteRepositoryDao;
    @Inject
    private PremioRepositoryDao premioRepositoryDao;
    @Inject
    private PromoRepositoryDao promoRepositoryDao;
    @Inject
    private BlacklistRepositoryDao blacklistRepositoryDao;
    @Inject
    private PromoContainerRepositoryDao promoContainerRepositoryDao;
    @Inject
    private Gson gson;

    public Resultado CalcularResultado(int promocionId) throws Exception {
        //Obtener Listado
        List<PremioResultadoDto> seleccionados = new ArrayList<>();
        List<Premio> premios = premioRepositoryDao.GetPremioListByPomoId(promocionId);
        Promocion promocion = promoRepositoryDao.GetPromoByIdComplete(promocionId);

        if (promocion == null) throw new Exception("Promocion no valida");

        PromoContainer promoContainer = promoContainerRepositoryDao.findById(promocion.getPromoContainer().getId());

        List<Blacklist> blacklists = blacklistRepositoryDao.getBlaclistByPromoContiner(promoContainer.getId());

        for (Premio premio : premios) {
            PremioResultadoDto premioResultadoDto = new PremioResultadoDto();
            premioResultadoDto.premio = premio;
            premioResultadoDto.seleccionados = new ArrayList<>();
            seleccionados.add(premioResultadoDto);
        }

        boolean ready = false;
        int iteration = 9999;
        while (ready == false) {
            ready = true;
            for (PremioResultadoDto seleccionado : seleccionados) {
                if (seleccionado.seleccionados.size() < (seleccionado.premio.getSuplente() + 1)) {
                    ready = false;
                    Client cliente = clienteRepositoryDao.GetRandomClientByPromoId(promocionId);
                    if (clientAlreadySelected(seleccionados, cliente) == false && isClientBlacklisted(blacklists, cliente) == false) {
                        seleccionado.seleccionados.add(cliente);
                    }
                }
            }
            iteration--;
            if (iteration < 0) {
                ready = true;
            }
        }

        Resultado resultado = new Resultado();
        resultado.promoId = promocionId;

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHMMSS");
        Date date = new Date();
        resultado.fecha = dateFormat.format(date);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.valueToTree(seleccionados);
        resultado.resultado = tree.toString();
        return resultado;
    }

    public Promocion getResultadoByPromoId(int promoId) {
        return promoRepositoryDao.findById(promoId);
    }

    public Promocion crearPremiacionEnPromo(int promoId, String resultado) {
        Promocion res = promoRepositoryDao.findById(promoId);

        res.setPremiacion(resultado);
        res.update();

        PremioResultadoDto[] premioResultadoDtoList = gson.fromJson(resultado, PremioResultadoDto[].class);

        for (PremioResultadoDto premioResultadoDto : premioResultadoDtoList) {
            for (Client client : premioResultadoDto.seleccionados) {
                blacklistRepositoryDao.addToBlacklist(res.getPromoContainer().getId(), client);
            }
        }
        return res;
    }

    private boolean isClientBlacklisted(List<Blacklist> blacklistlists, Client actual) {
        for (Blacklist blacklist : blacklistlists) {
            if (blacklist.getMsisdn().equals(actual.getMsisdn()))
                return true;
        }
        return false;
    }

    private boolean clientAlreadySelected(List<PremioResultadoDto> seleccionados, Client actual) {
        for (PremioResultadoDto resultado : seleccionados) {
            for (Client seleccionado : resultado.seleccionados) {
                if (seleccionado != null && seleccionado.getMsisdn().equals(actual.getMsisdn()))
                    return true;
            }
        }
        return false;
    }
}

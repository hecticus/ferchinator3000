package applicationservices.premiacionesservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import interfaceadapters.premiorepositorydao.ClienteRepositoryDao;
import interfaceadapters.premiorepositorydao.PremioRepositoryDao;
import interfaceadapters.premiorepositorydao.PromoRepositoryDao;
import interfaceadapters.premiorepositorydao.ResultadoRepositoryDao;
import models.Client;
import models.Premio;
import models.Resultado;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class PremiacionesService {

    private ClienteRepositoryDao clienteRepositoryDao;
    private PremioRepositoryDao premioRepositoryDao;
    private PromoRepositoryDao promoRepositoryDao;
    private ResultadoRepositoryDao resultadoRepositoryDao;
    private Gson gson;

    @Inject
    public PremiacionesService(ClienteRepositoryDao clienteRepositoryDao, PremioRepositoryDao premioRepositoryDao, PromoRepositoryDao promoRepositoryDao, ResultadoRepositoryDao resultadoRepositoryDao) {
        this.clienteRepositoryDao = clienteRepositoryDao;
        this.premioRepositoryDao = premioRepositoryDao;
        this.promoRepositoryDao = promoRepositoryDao;
        this.resultadoRepositoryDao = resultadoRepositoryDao;
        gson = new Gson();
    }

    public Resultado CalcularResultado(int promocion) {
        //Obtener Listado
        List<PremioResultadoDto> seleccionados = new ArrayList<>();
        List<Premio> premios = premioRepositoryDao.GetPremioListByPomoId(promocion);

        for (Premio premio: premios) {
            PremioResultadoDto premioResultadoDto = new PremioResultadoDto();
            premioResultadoDto.premio = premio;
            premioResultadoDto.seleccionados = new ArrayList<>();
            seleccionados.add(premioResultadoDto);
        }

        boolean ready = false;
        int iteration = 9999;
        while (ready == false) {
            ready = true;
            for (PremioResultadoDto seleccionado: seleccionados) {
                if(seleccionado.seleccionados.size()< (seleccionado.premio.getSuplente() + 1)) {
                    ready = false;
                    Client cliente = clienteRepositoryDao.GetRandomClientByPromoId(promocion);
                    if(clientAlreadySelected(seleccionados, cliente) == false) {
                        seleccionado.seleccionados.add(cliente);
                    }
                }
            }
            iteration--;
            if(iteration <0){
                ready = true;
            }
        }

        Resultado resultado = new Resultado();
        resultado.setPromoId(promocion);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHMMSS");
        Date date = new Date();
        resultado.setFecha(dateFormat.format(date));
        ResultadoEntity res = new ResultadoEntity(resultado);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.valueToTree(seleccionados);
        resultado.setResultado(tree.toString());
        resultado.insert();
        return resultado;
    }

    public Resultado getResultadoByPromoId(int promoId){
        Resultado res = resultadoRepositoryDao.GetPremioListByPromoId(promoId);
        return  res;
    }
    
    private boolean clientAlreadySelected(List<PremioResultadoDto> seleccionados, Client actual){
        for (PremioResultadoDto resultado: seleccionados) {
            for (Client seleccionado: resultado.seleccionados) {
                if(seleccionado.getMsisdn().equals(actual.getMsisdn()))
                    return true;
            }
        }
        return false;
    }


}

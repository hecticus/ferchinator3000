package interfaceadapters.premiorepositorydao;

import javassist.NotFoundException;
import models.Configuracion;
import models.Promocion;
import util.AbstractDao;

import javax.inject.Singleton;

@Singleton
public class ConfiguracionRepositoryDao extends AbstractDao<Integer, Configuracion> {

    public ConfiguracionRepositoryDao() {
        super(Configuracion.class);
    }

    public String obtenerPorLlave(String llave) throws Exception {
        Configuracion conf = find.query().where().eq("llave", llave).findOne();
        if(conf == null)
            throw new NotFoundException("Llave no encontrada");
        return conf.getValor();
    }
}

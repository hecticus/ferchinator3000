package interfaceadapters.premiorepositorydao;

import models.Client;
import models.Premio;
import models.Resultado;
import util.AbstractDao;


import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ResultadoRepositoryDao extends AbstractDao<Integer, Resultado> {

    public ResultadoRepositoryDao() {
        super(Resultado.class);
    }

    public Resultado GetPremioListByPromoId(int promoId) {
        Resultado resultado = find.query().where().orderBy("id desc").setMaxRows(1).findOne();
        return resultado;
    }
}

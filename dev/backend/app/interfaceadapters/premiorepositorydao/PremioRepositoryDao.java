package interfaceadapters.premiorepositorydao;

import models.Premio;
import util.AbstractDao;


import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PremioRepositoryDao extends AbstractDao<Integer, Premio> {

    public PremioRepositoryDao() {
        super(Premio.class);
    }

    public List<Premio> GetPremioListByPomoId(int promoId) {
        List<Premio> premios = find.query().where().eq("promo_id", promoId).findList();
        return premios;
    }
}

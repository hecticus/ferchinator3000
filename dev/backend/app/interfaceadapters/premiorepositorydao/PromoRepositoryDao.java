package interfaceadapters.premiorepositorydao;

import models.Promocion;
import util.AbstractDao;


import javax.inject.Singleton;

@Singleton
public class PromoRepositoryDao extends AbstractDao<Integer, Promocion> {

    public PromoRepositoryDao() {
        super(Promocion.class);
    }
}

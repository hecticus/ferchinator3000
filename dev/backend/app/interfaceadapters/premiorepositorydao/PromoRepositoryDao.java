package interfaceadapters.premiorepositorydao;

import models.PromoContainer;
import models.Promocion;
import util.AbstractDao;


import javax.inject.Singleton;

@Singleton
public class PromoRepositoryDao extends AbstractDao<Integer, Promocion> {

    public PromoRepositoryDao() {
        super(Promocion.class);
    }

    public Promocion GetPromoByIdComplete(int id){
        return find.query()
                .where().eq("id", id).findOne();
    }
}

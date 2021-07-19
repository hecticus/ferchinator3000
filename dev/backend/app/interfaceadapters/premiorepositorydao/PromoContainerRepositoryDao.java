package interfaceadapters.premiorepositorydao;

import models.Blacklist;
import models.PromoContainer;
import util.AbstractDao;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PromoContainerRepositoryDao extends AbstractDao<Integer, PromoContainer> {

    public PromoContainerRepositoryDao() {
        super(PromoContainer.class);
    }


}

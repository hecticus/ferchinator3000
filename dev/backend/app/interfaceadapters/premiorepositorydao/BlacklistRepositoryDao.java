package interfaceadapters.premiorepositorydao;

import models.Blacklist;
import models.Client;
import models.Premio;
import util.AbstractDao;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class BlacklistRepositoryDao extends AbstractDao<Integer, Blacklist> {

    @Inject
    PromoContainerRepositoryDao promoContainerRepositoryDao;

    public BlacklistRepositoryDao() {
        super(Blacklist.class);
    }

    public List<Blacklist> getBlaclistByPromoContiner(int promoContainerId) {
        List<Blacklist> blacklist = find.query().where().eq("promo_container_id", promoContainerId).findList();
        return blacklist;
    }

    public void addToBlacklist(int promoContainerId, String msisdn) {
        Blacklist blacklist = new Blacklist();
        blacklist.setMsisdn(msisdn);
        blacklist.setPromoContainer(promoContainerRepositoryDao.findById(promoContainerId));
        blacklist.save();
    }

    public void addToBlacklist(int promoContainerId, Client client) {
        Blacklist blacklist = new Blacklist();
        blacklist.setMsisdn(client.getMsisdn());
        blacklist.setPromoContainer(promoContainerRepositoryDao.findById(promoContainerId));
        blacklist.save();
    }
}

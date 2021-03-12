package interfaceadapters.premiorepositorydao;

import models.Client;
import util.AbstractDao;

import javax.inject.Singleton;

@Singleton
public class ClienteRepositoryDao extends AbstractDao<Integer, Client> {

    public ClienteRepositoryDao() {
        super(Client.class);
    }

    public Client GetRandomClientByPromoId(int promoId)
    {
        Client cliente = find.query().where().orderBy("RAND()").setMaxRows(1).findOne();
        return cliente;
    }
}

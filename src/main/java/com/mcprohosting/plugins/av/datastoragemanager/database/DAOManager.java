package com.mcprohosting.plugins.av.datastoragemanager.database;

import com.mcprohosting.plugins.av.datastoragemanager.database.dao.NetworkUserDAO;
import com.mcprohosting.plugins.av.datastoragemanager.database.dao.NetworkUserPurchaseDAO;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUser;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUserPurchase;
import lombok.Getter;
import org.mongodb.morphia.Datastore;

public class DAOManager {

    @Getter private static NetworkUserDAO networkUserDAO;
    @Getter private static NetworkUserPurchaseDAO networkUserPurchaseDAO;

    public DAOManager(MongoResource resource) {
        Datastore datastore = resource.getDatastore();
        ensureIndexes(datastore);
        registerDAO(datastore);
    }

    private void registerDAO(Datastore datastore) {
        networkUserDAO = new NetworkUserDAO(datastore);
        networkUserPurchaseDAO = new NetworkUserPurchaseDAO(datastore);
    }

    private void ensureIndexes(Datastore datastore) {
        datastore.ensureIndexes(NetworkUser.class);
        datastore.ensureIndexes(NetworkUserPurchase.class);
    }

}

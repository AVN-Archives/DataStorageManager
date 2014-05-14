package com.mcprohosting.plugins.av.datastoragemanager.database;

import com.mcprohosting.plugins.av.datastoragemanager.database.dao.NetworkSettingsDAO;
import com.mcprohosting.plugins.av.datastoragemanager.database.dao.NetworkUserDAO;
import com.mcprohosting.plugins.av.datastoragemanager.database.dao.NetworkUserPurchaseDAO;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkSettings;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUser;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUserPurchase;
import lombok.Getter;
import org.mongodb.morphia.Datastore;

public class DAOManager {

    @Getter private static Datastore datastore;

    @Getter private static NetworkSettingsDAO networkSettingsDAO;
    @Getter private static NetworkUserDAO networkUserDAO;
    @Getter private static NetworkUserPurchaseDAO networkUserPurchaseDAO;

    public DAOManager(MongoResource resource) {
        datastore = resource.getDatastore();
        ensureIndexes(datastore);
        registerDAO(datastore);
    }

    private void registerDAO(Datastore datastore) {
        networkSettingsDAO = new NetworkSettingsDAO(datastore);
        networkUserDAO = new NetworkUserDAO(datastore);
        networkUserPurchaseDAO = new NetworkUserPurchaseDAO(datastore);
    }

    private void ensureIndexes(Datastore datastore) {
        datastore.ensureIndexes(NetworkSettings.class);
        datastore.ensureIndexes(NetworkUser.class);
        datastore.ensureIndexes(NetworkUserPurchase.class);
    }

}

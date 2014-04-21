package com.mcprohosting.plugins.av.datastoragemanager.database;

import com.mcprohosting.plugins.av.datastoragemanager.database.dao.NetworkUserDAO;
import lombok.Getter;
import org.mongodb.morphia.Datastore;

public class DAOManager {

    @Getter
    public static NetworkUserDAO networkUserDAO;

    public DAOManager(MongoResource resource) {
        Datastore datastore = resource.getDatastore();
        datastore.ensureIndexes();

        networkUserDAO = new NetworkUserDAO(datastore);
    }

}

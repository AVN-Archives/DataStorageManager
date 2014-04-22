package com.mcprohosting.plugins.av.datastoragemanager.database.dao;

import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUserPurchase;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class NetworkUserPurchaseDAO extends BasicDAO<NetworkUserPurchase, ObjectId> {

    public NetworkUserPurchaseDAO(Datastore ds) {
        super(ds);
    }

}

package com.mcprohosting.plugins.av.datastoragemanager.database.dao;

import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUser;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class NetworkUserDAO extends BasicDAO<NetworkUser, ObjectId>{

    public NetworkUserDAO(Datastore ds) {
        super(ds);
    }

}

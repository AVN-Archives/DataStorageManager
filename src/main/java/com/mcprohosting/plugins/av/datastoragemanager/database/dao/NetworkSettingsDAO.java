package com.mcprohosting.plugins.av.datastoragemanager.database.dao;

import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkSettings;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class NetworkSettingsDAO extends BasicDAO<NetworkSettings, ObjectId> {

    public NetworkSettingsDAO(Datastore ds) {
        super(ds);
    }

}

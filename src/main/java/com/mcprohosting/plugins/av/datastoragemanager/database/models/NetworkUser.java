package com.mcprohosting.plugins.av.datastoragemanager.database.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "user", noClassnameStored = true)
public class NetworkUser {

    @Id
    private ObjectId id;

    @Embedded("preferences")
    private NetworkUserPreferences networkUserPreferences;

}

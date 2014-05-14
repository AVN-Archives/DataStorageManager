package com.mcprohosting.plugins.av.datastoragemanager.database.models;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "networksettings", noClassnameStored = true)
public class NetworkSettings {

    public NetworkSettings() {
        coinMultiplier = 0;
        chatMode = "PUBLIC";
        chatDelay = 0;
    }

    @Id
    @Getter
    private ObjectId id;

    @Getter
    private int coinMultiplier;

    @Getter
    private String chatMode;

    @Getter
    private long chatDelay;

}

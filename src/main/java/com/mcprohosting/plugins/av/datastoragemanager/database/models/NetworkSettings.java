package com.mcprohosting.plugins.av.datastoragemanager.database.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "networksettings", noClassnameStored = true)
public class NetworkSettings {

    public NetworkSettings() {
        coinMultiplier = 1;
        chatMode = "PUBLIC";
        chatDelay = 0;
    }

    @Id
    @Getter
    private ObjectId id;

    @Getter @Setter
    private int coinMultiplier;

    @Getter @Setter
    private String chatMode;

    @Getter @Setter
    private long chatDelay;

}

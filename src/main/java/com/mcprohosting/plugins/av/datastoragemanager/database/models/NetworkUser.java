package com.mcprohosting.plugins.av.datastoragemanager.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexDirection;

import java.util.ArrayList;
import java.util.List;

@Entity(value = "users", noClassnameStored = true)
@NoArgsConstructor
public class NetworkUser {

    public NetworkUser(String uuid) {
        this.uuid = uuid;
        this.name = "";
        this.ip = "";
        this.rank = "DEFAULT";
        this.networkUserPreferences = new NetworkUserPreferences();
        this.networkUserModeration = new NetworkUserModeration();
    }

    @Id
    @Getter
    private ObjectId id;

    @Indexed(value = IndexDirection.ASC, name = "uuid", unique = true)
    @Getter
    private String uuid;

    @Indexed(value = IndexDirection.ASC, name = "name")
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String ip;

    @Getter @Setter
    private String rank;

    @Getter @Setter
    private int coins;

    @Embedded("preferences")
    @Getter
    private NetworkUserPreferences networkUserPreferences;

    @Embedded("moderation")
    @Getter
    private NetworkUserModeration networkUserModeration;

    @Reference("purchases")
    @Getter
    private List<NetworkUserPurchase> networkUserPurchases = new ArrayList<NetworkUserPurchase>();

}

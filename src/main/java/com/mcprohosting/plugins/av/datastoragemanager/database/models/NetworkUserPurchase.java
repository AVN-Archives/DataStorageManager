package com.mcprohosting.plugins.av.datastoragemanager.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import javax.persistence.PrePersist;
import java.util.Date;

@Entity(value = "purchases", noClassnameStored = true)
@NoArgsConstructor
public class NetworkUserPurchase {

    public NetworkUserPurchase(String type, String data) {
        this.type = type;
        this.data = data;
    }

    @Id
    @Getter
    private ObjectId id;

    @Getter
    private String type;

    @Getter
    private String data;

    @Property(value = "purchase_date")
    private Date purchaseDate;

    @PrePersist
    public void prePersist() {
        purchaseDate = (purchaseDate == null) ? new Date() : purchaseDate;
    }

}

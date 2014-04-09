package com.mcprohosting.plugins.av.datastoragemanager.beans;

import com.avaje.ebean.BeanState;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "network_user_purchases", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@NoArgsConstructor
public class NetworkUserPurchase {

    public NetworkUserPurchase(NetworkUser user) {
        setNetworkUser(user);

        user.getPurchases().add(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Integer id;

    @ManyToOne(optional = false)
    @Getter @Setter private NetworkUser networkUser;

    @Getter @Setter private String type;
    @Getter @Setter private String data;

    @CreatedTimestamp
    @Getter @Setter private Timestamp datePurchased;

    public void save() {
        BeanState state = DataStorageManager.getAvajeDatabase().getServer().getBeanState(this);
        if (state.isNewOrDirty()) {
            DataStorageManager.getAvajeDatabase().getServer().save(this);
        }
    }
}

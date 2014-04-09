package com.mcprohosting.plugins.av.datastoragemanager.beans;

import com.avaje.ebean.BeanState;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.CreatedTimestamp;
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
    }

    @ManyToOne(optional = false)
    @Getter @Setter private NetworkUser networkUser;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private int id;

    @Getter @Setter private String type;
    @Getter @Setter private String data;

    @CreatedTimestamp
    @Getter @Setter private Timestamp purchaseDate;

    public void save() {
        BeanState state = Ebean.getBeanState(this);
        if (state.isNewOrDirty()) {
            Ebean.save(this);
        }
    }
}

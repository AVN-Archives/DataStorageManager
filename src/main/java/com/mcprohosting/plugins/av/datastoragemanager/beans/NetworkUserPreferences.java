package com.mcprohosting.plugins.av.datastoragemanager.beans;

import com.avaje.ebean.BeanState;
import com.avaje.ebean.Ebean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "network_user_preferences", uniqueConstraints = {@UniqueConstraint(columnNames = {"network_user_id"})})
@NoArgsConstructor
public class NetworkUserPreferences implements Serializable {

    public NetworkUserPreferences(NetworkUser user) {
        setNetworkUser(user);
    }

    @OneToOne
    @Getter @Setter NetworkUser networkUser;

    @Getter @Setter Boolean vanished;

    @Version
    @Getter @Setter Timestamp lastUpdate;

    public void save() {
        BeanState state = Ebean.getBeanState(this);
        if (state.isNewOrDirty()) {
            Ebean.save(this);
        }
    }

}

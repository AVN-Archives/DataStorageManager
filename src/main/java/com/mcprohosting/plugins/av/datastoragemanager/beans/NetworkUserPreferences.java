package com.mcprohosting.plugins.av.datastoragemanager.beans;

import com.avaje.ebean.BeanState;
import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
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
    @Getter @Setter private NetworkUser networkUser;

    @Getter @Setter private boolean vanished;

    @Version
    @Getter @Setter private Timestamp lastUpdate;

    public void save() {
        BeanState state = DataStorageManager.getAvajeDatabase().getServer().getBeanState(this);
        if (state.isNewOrDirty()) {
            DataStorageManager.getAvajeDatabase().getServer().save(this);
        }
    }

}

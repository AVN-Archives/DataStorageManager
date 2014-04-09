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
@Table(name = "network_user_moderation", uniqueConstraints = {@UniqueConstraint(columnNames = {"network_user_id"})})
@NoArgsConstructor
public class NetworkUserModeration implements Serializable {

    public NetworkUserModeration(NetworkUser user) {
        setNetworkUser(user);
    }

    @OneToOne
    @Getter @Setter private NetworkUser networkUser;

    @Getter @Setter private boolean muted;
    @Getter @Setter private long muteEndTime;
    @Getter @Setter private boolean banned;
    @Getter @Setter private long banEndTime;
    @Getter @Setter private String banReason;

    @Version
    @Getter @Setter private Timestamp lastUpdate;

    public void save() {
        BeanState state = DataStorageManager.getAvajeDatabase().getServer().getBeanState(this);
        if (state.isNewOrDirty()) {
            DataStorageManager.getAvajeDatabase().getServer().save(this);
        }
    }

}

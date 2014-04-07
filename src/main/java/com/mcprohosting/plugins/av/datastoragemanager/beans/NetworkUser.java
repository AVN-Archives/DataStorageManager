package com.mcprohosting.plugins.av.datastoragemanager.beans;

import com.avaje.ebean.BeanState;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "network_users")
public class NetworkUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @Getter @Setter Integer id;

    @JoinTable(name = "network_user_preferences",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id", unique = true)})
    @Getter @Setter NetworkUserPreferences preferences;

    @Getter @Setter String UUID;
    @Getter @Setter Integer coins;

    @CreatedTimestamp
    @Getter @Setter Timestamp joinTime;

    @Version
    @Getter @Setter Timestamp lastUpdate;

    public void init(Player player) {
        if (id == null) {
            setUUID(player.getUniqueId().toString());
            setCoins(0);

            save();
        }

        preferences = Ebean.find(NetworkUserPreferences.class).where().eq("network_user_id", id.toString()).findUnique();

        if (preferences == null) {
            preferences = new NetworkUserPreferences(this);
            preferences.setVanished(false);

            preferences.save();
        }
    }

    public void save() {
        BeanState state = Ebean.getBeanState(this);
        if (state.isNewOrDirty()) {
            Ebean.save(this);
        }
    }

    public void saveAll() {
        save();
        preferences.save();
    }

}

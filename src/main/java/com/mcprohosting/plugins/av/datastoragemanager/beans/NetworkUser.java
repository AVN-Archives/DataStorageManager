package com.mcprohosting.plugins.av.datastoragemanager.beans;

import com.avaje.ebean.BeanState;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.CreatedTimestamp;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "network_users")
public class NetworkUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @Getter @Setter private Integer id;

    @Getter @Setter private NetworkUserPreferences preferences;

    @Getter @Setter private NetworkUserModeration moderation;

    @Getter @Setter private Set<NetworkUserPurchase> purchases;

    @Getter @Setter private Set<NetworkUserAchievement> achievements;

    @Getter @Setter private String UUID;
    @Getter @Setter private int coins;

    @CreatedTimestamp
    @Getter @Setter private Timestamp dateJoined;

    @Version
    @Getter @Setter private Timestamp lastUpdate;

    public void init(Player player) {
        if (id == null) {
            setUUID(player.getUniqueId().toString());
            save();
        }

        Collection<Object> beans = new HashSet();

        preferences = Ebean.find(NetworkUserPreferences.class).where().eq("network_user_id", id.toString()).findUnique();
        moderation = Ebean.find(NetworkUserModeration.class).where().eq("network_user_id", id.toString()).findUnique();
        purchases = Ebean.find(NetworkUserPurchase.class).where().eq("network_user_id", id.toString()).findSet();
        achievements = Ebean.find(NetworkUserAchievement.class).where().eq("network_user_id", id.toString()).findSet();

        if (preferences == null) {
            preferences = new NetworkUserPreferences(this);
            beans.add(preferences);
        }

        if (moderation == null) {
            moderation = new NetworkUserModeration(this);
            beans.add(preferences);
        }

        if (purchases == null) {
            purchases = new HashSet<>();
        }

        if (achievements == null) {
            achievements = new HashSet<>();
        }

        if (beans.size() > 0) {
            Ebean.save(beans);
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
        moderation.save();

        for (NetworkUserPurchase purchase : purchases) {
            purchase.save();
        }

        for (NetworkUserAchievement achievement : achievements) {
            achievement.save();
        }
    }

}

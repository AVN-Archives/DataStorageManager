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
@Table(name = "network_user_achievements", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@NoArgsConstructor
public class NetworkUserAchievement {

    public NetworkUserAchievement(NetworkUser user, NetworkAchievement achievement) {
        setNetworkUser(user);
        setNetworkAchievement(achievement);

        user.getAchievements().add(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Integer id;

    @ManyToOne
    @Getter @Setter private NetworkUser networkUser;

    @ManyToOne
    @Getter @Setter private NetworkAchievement networkAchievement;

    @CreatedTimestamp
    @Getter @Setter private Timestamp dateEarned;

    public void save() {
        BeanState state = Ebean.getBeanState(this);
        if (state.isNewOrDirty()) {
            Ebean.save(this);
        }
    }

}

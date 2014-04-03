package com.mcprohosting.plugins.av.datastoragemanager.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "network_user_preferences")
@NoArgsConstructor
public class NetworkUserPreferences {

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private NetworkUser networkUser;

    @Id
    @Getter @Setter Integer id;

    @Getter @Setter Boolean vanished;

    @Version
    @Getter @Setter Timestamp lastUpdate;

    public NetworkUserPreferences(NetworkUser networkUser) {
        this.networkUser = networkUser;
    }

}

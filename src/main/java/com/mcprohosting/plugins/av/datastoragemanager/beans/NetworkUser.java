package com.mcprohosting.plugins.av.datastoragemanager.beans;

import com.avaje.ebean.annotation.CreatedTimestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.sql.Timestamp;

@Entity
@Table(name = "network_users")
@NoArgsConstructor
public class NetworkUser {

    @Id
    @Getter @Setter Integer id;

    @Getter @Setter String UUID;
    @Getter @Setter Integer coins;

    @CreatedTimestamp
    @Getter @Setter Timestamp joinTime;

    @Version
    @Getter @Setter Timestamp lastUpdate;

}

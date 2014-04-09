package com.mcprohosting.plugins.av.datastoragemanager.beans;

import com.avaje.ebean.BeanState;
import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "network_achievements", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@NoArgsConstructor
public class NetworkAchievement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private int id;

    @Getter @Setter private String name;

    @Getter @Setter private String description;
    @Getter @Setter private String data;

    public void save() {
        BeanState state = DataStorageManager.getAvajeDatabase().getServer().getBeanState(this);
        if (state.isNewOrDirty()) {
            DataStorageManager.getAvajeDatabase().getServer().save(this);
        }
    }

}

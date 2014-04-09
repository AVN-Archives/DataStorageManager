package com.mcprohosting.plugins.av.datastoragemanager.beans;

import com.avaje.ebean.BeanState;
import com.avaje.ebean.Ebean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "network_achievements", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@NoArgsConstructor
public class NetworkAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private int id;

    @Getter @Setter private String name;

    @Getter @Setter private String description;
    @Getter @Setter private String data;

    public void save() {
        BeanState state = Ebean.getBeanState(this);
        if (state.isNewOrDirty()) {
            Ebean.save(this);
        }
    }

}

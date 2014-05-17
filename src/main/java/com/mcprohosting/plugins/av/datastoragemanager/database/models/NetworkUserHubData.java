package com.mcprohosting.plugins.av.datastoragemanager.database.models;

import com.mcprohosting.plugins.av.datastoragemanager.util.SerializableVector;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mongodb.morphia.annotations.Serialized;

@NoArgsConstructor
public class NetworkUserHubData {

    @Getter @Setter
    @Serialized
    private SerializableVector parkourCheckpoint;

}

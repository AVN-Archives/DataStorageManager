package com.mcprohosting.plugins.av.datastoragemanager.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mongodb.morphia.annotations.Property;

@NoArgsConstructor
public class NetworkUserModeration {

    @Getter @Setter
    private boolean banned;

    @Property(value = "ban_end_time")
    @Getter @Setter
    private long banEndTime;

    @Property(value = "ban_reason")
    @Getter @Setter
    private String banReason;

    @Getter @Setter
    private boolean muted;

    @Property(value = "mute_end_time")
    @Getter @Setter
    private long muteEndTime;

}

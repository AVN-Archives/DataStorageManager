package com.mcprohosting.plugins.av.datastoragemanager.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class NetworkUserPreferences {

    @Getter @Setter
    private boolean vanished = false;

    @Getter @Setter
    private boolean receiveMessages = true;

}

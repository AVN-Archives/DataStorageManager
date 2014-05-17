package com.mcprohosting.plugins.av.datastoragemanager.util;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.Serializable;

public class SerializableVector implements Serializable {

    static final long serialVersionUID = 1325437871047901707L;

    @Getter
    private int x, y, z;

    public SerializableVector(Location location) {
        x = location.getBlockX();
        y = location.getBlockY();
        z = location.getBlockZ();
    }

    public Location getLocation(Player player) {
        return new Location(player.getWorld(), x + 0.5, y + 0.125, z + 0.5);
    }

}

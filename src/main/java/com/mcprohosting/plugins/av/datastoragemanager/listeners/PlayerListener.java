package com.mcprohosting.plugins.av.datastoragemanager.listeners;

import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import com.mcprohosting.plugins.av.datastoragemanager.api.DataAPI;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLogin(AsyncPlayerPreLoginEvent event) {
        Long start = System.currentTimeMillis();

        NetworkUser user = DataAPI.initUser(event.getUniqueId().toString(), event.getName(), event.getAddress().getHostAddress());

        DataStorageManager.getInstance().getLogger().info("Processing Time: " + (System.currentTimeMillis() - start) + "ms");
    }

}

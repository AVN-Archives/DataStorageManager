package com.mcprohosting.plugins.av.datastoragemanager.listeners;

import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import com.mcprohosting.plugins.av.datastoragemanager.api.DataAPI;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLogin(PlayerLoginEvent event) {
        Long start = System.currentTimeMillis();

        Player player = event.getPlayer();
        NetworkUser user = DataAPI.getUser(player.getUniqueId().toString());

        DataStorageManager.getInstance().getLogger().info("Processing Time: " + (System.currentTimeMillis() - start) + "ms");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        DataAPI.getMappedUsers().remove(player.getUniqueId());
    }

}

package com.mcprohosting.plugins.av.datastoragemanager.listeners;

import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import com.mcprohosting.plugins.av.datastoragemanager.beans.NetworkUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {

    private Map<UUID, NetworkUser> networkUsers;

    public PlayerListener() {
        networkUsers = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        NetworkUser networkUser = DataStorageManager.getAvajeDatabase().getServer().find(NetworkUser.class)
                .where()
                .eq("UUID", player.getUniqueId().toString())
                .findUnique();

        if (networkUser == null) {
            networkUser = new NetworkUser();
        }

        networkUser.init(player);
        addUser(player, networkUser);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        removeUser(player);
    }

    private void addUser(Player player, NetworkUser networkUser) {
        networkUsers.put(player.getUniqueId(), networkUser);
    }

    private void removeUser(Player player) {
        NetworkUser user = networkUsers.remove(player.getUniqueId());
        user.saveAll();
    }

}

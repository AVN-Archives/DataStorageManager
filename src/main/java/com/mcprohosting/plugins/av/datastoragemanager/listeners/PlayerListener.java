package com.mcprohosting.plugins.av.datastoragemanager.listeners;

import com.avaje.ebean.Ebean;
import com.mcprohosting.plugins.av.datastoragemanager.beans.NetworkUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {

    private Map<UUID, NetworkUser> networkUsers;

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        NetworkUser networkUser = Ebean.find(NetworkUser.class)
                .where()
                .eq("UUID", player.getUniqueId().toString())
                .findUnique();

        if (networkUser == null) {
            networkUser = new NetworkUser();
            networkUser.setUUID(player.getUniqueId().toString());
            networkUser.setCoins(0);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
    }

    private void addUser(Player player, NetworkUser networkUser) {
        networkUsers.put(player.getUniqueId(), networkUser);
    }

    private void removeUser(Player player) {
        NetworkUser networkUser = networkUsers.remove(player.getUniqueId());
        Ebean.save(networkUser);
    }

}

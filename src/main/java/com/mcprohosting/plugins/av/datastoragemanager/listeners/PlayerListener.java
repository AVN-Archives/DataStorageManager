package com.mcprohosting.plugins.av.datastoragemanager.listeners;

import com.avaje.ebean.Ebean;
import com.mcprohosting.plugins.av.datastoragemanager.beans.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {

    private Map<UUID, User> users;

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        User user = Ebean.find(User.class)
                .where()
                .eq("UUID", player.getUniqueId().toString())
                .findUnique();

        if (user == null) {
            user = new User();
            user.setUUID(player.getUniqueId().toString());
            user.setCoins(0);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
    }

    private void addUser(Player player, User user) {
        users.put(player.getUniqueId(), user);
    }

    private void removeUser(Player player) {
        User user = users.remove(player.getUniqueId());
        Ebean.save(user);
    }

}

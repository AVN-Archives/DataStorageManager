package com.mcprohosting.plugins.av.datastoragemanager.util;

import com.mcprohosting.plugins.av.datastoragemanager.database.DAOManager;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUser;
import org.bukkit.entity.Player;

public class DataUtil {

    public static void updateNetworkUser(NetworkUser user, Player player) {
        if (user.getName().equalsIgnoreCase(player.getName()) == false) {
            user.setName(player.getName());
        }

        if (user.getIp().equalsIgnoreCase(player.getAddress().getAddress().getHostAddress()) == false) {
            user.setIp(player.getAddress().getAddress().getHostAddress());
        }

        DAOManager.getNetworkUserDAO().save(user);
    }

}

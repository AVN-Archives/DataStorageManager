package com.mcprohosting.plugins.av.datastoragemanager.util;

import com.mcprohosting.plugins.av.datastoragemanager.database.DAOManager;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUser;

public class DataUtil {

    public static void updateNetworkUser(NetworkUser user, String name, String ip) {
        if (user.getName() == null || user.getName().equalsIgnoreCase(name) == false) {
            user.setName(name);
        }

        if (user.getIp() == null || user.getIp().equalsIgnoreCase(ip) == false) {
            user.setIp(ip);
        }

        DAOManager.getNetworkUserDAO().save(user);
    }

    public static void validate(NetworkUser user) {
        if (user.getRank() == null) {
            user.setRank("DEFAULT");
        }
    }

}

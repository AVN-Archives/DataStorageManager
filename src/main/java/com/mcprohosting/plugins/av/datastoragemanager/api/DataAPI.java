package com.mcprohosting.plugins.av.datastoragemanager.api;

import com.mcprohosting.plugins.av.datastoragemanager.beans.NetworkUser;
import com.mcprohosting.plugins.av.datastoragemanager.beans.NetworkUserPurchase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataAPI {

    private static Map<String, NetworkUser> users;

    static {
        users = new HashMap<>();
    }

    /**
     * Add a user to the users map
     *
     * @param uuid the uuid of the user
     * @param user the ebean of the user
     */
    public static void addUser(String uuid, NetworkUser user) {
        users.put(uuid, user);
    }

    /**
     * Remove a user from the users map and save the user ebeans
     *
     * @param uuid the uuid of the user
     */
    public static void removeUser(String uuid) {
        NetworkUser user = users.remove(uuid);
        user.saveAll();
    }

    /**
     * Get a user with the specified uuid
     *
     * @param uuid the uuid of the user
     *
     * @return the network user ebean mapped to the specified uuid
     */
    public static NetworkUser getUser(String uuid) {
        return users.get(uuid);
    }

    /**
     * Get all network user ebeans as a collection
     *
     * @return collection of network user ebeans
     */
    public static Collection<NetworkUser> getUsers() {
        return users.values();
    }

    /**
     * Save a users data. If a bean is dirty (contains changes) or is new
     * it will be saved to the database.
     *
     * @param user the network user ebean of the user
     * @param saveAll save all data beans of this network user if true, otherwise save only the network user bean
     */
    public static void saveUser(NetworkUser user, boolean saveAll) {
        if (saveAll) {
            user.saveAll();
        } else {
            user.save();
        }
    }

    /**
     * Save all of a users data. If a bean is dirty (contains changes) or is new
     * it will be saved to the database.
     *
     * @param user the network user ebean of the user
     */
    public static void saveAll(NetworkUser user) {
        user.saveAll();
    }

    /**
     * Save a collection of users. If a bean is dirty (contains changes) or is new
     * it will be saved to the database.
     *
     * @param users a collection of network users
     * @param saveAll save all data beans of this network user if true, otherwise save only the network user bean
     */
    public static void saveMany(Collection<NetworkUser> users, boolean saveAll) {
        for (NetworkUser user : users) {
            if (saveAll) {
                user.saveAll();
            } else {
                user.save();
            }
        }
    }

    /**
     * Save all data of a collection of users. If a bean is dirty (contains changes) or is new
     * it will be saved to the database.
     *
     * @param users a collection of network users
     */
    public static void saveAllMany(Collection<NetworkUser> users) {
        for (NetworkUser user : users) {
            user.saveAll();
        }
    }

    /**
     * Create a transaction and optionally save the purchase immediately.
     *
     * @param user the ebean of the user
     * @param type the type (or category) of the transaction
     * @param data the data of the purchase as a string
     * @param save whether the purchase should be saved now or later
     */
    public static void createTransaction(NetworkUser user, String type, String data, boolean save) {
        NetworkUserPurchase purchase = new NetworkUserPurchase(user);
        purchase.setType(type);
        purchase.setData(data);

        if (save) {
            purchase.save();
        }
    }

}

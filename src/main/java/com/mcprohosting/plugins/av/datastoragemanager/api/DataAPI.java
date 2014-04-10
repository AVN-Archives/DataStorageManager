package com.mcprohosting.plugins.av.datastoragemanager.api;

import com.mcprohosting.plugins.av.datastoragemanager.beans.NetworkUser;

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

}

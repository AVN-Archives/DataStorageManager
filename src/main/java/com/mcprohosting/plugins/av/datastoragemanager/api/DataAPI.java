package com.mcprohosting.plugins.av.datastoragemanager.api;

import com.gmail.favorlock.commonutils.network.UUIDFetcher;
import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import com.mcprohosting.plugins.av.datastoragemanager.beans.NetworkUser;
import com.mcprohosting.plugins.av.datastoragemanager.beans.NetworkUserPurchase;
import org.bukkit.entity.Player;

import java.util.*;

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
     * Retrieve a network user instance from the users map if it exists,
     * otherwise retrieve the user instance from the database and
     * optionally store the network user into the user map.
     *
     * @param uuid the uuid of the user
     * @param storeUser store the user into the user map if true
     *
     * @return NetworkUser instance
     */
    public static NetworkUser retrieveUser(String uuid, boolean storeUser) {
        if (users.containsKey(uuid)) {
            return users.get(uuid);
        }

        NetworkUser user = DataStorageManager.getAvajeDatabase().getServer().find(NetworkUser.class)
                .where()
                .eq("UUID", uuid)
                .findUnique();

        if (user == null) {
            user = new NetworkUser();
        }

        user.init(uuid);

        if (storeUser) {
            DataAPI.addUser(uuid, user);
        }



        return user;
    }

    /**
     * Remove a user from the users map and save the user ebeans
     *
     * @param uuid the uuid of the user
     */
    public static void removeUser(String uuid) {
        NetworkUser user = users.remove(uuid);
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

    /**
     * Gets the UUID of a player.
     *
     * @param name of the player
     *
     * @return uuid of the player, will return null if not found
     */
    public static String getPlayerUUID(String name) {
        UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(name));

        Map<String, UUID> response = null;
        try {
            response = fetcher.call();
        } catch (Exception e) {
            DataStorageManager.getInstance().getLogger().warning("Exception while running UUIDFetcher");
            e.printStackTrace();
        }

        if (response.get(name) == null) {
            return null;
        }

        return response.get(name).toString();
    }

}

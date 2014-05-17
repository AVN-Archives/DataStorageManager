package com.mcprohosting.plugins.av.datastoragemanager.api;

import com.mcprohosting.plugins.av.datastoragemanager.database.DAOManager;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkSettings;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUser;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUserPurchase;
import com.mcprohosting.plugins.av.datastoragemanager.util.DataUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class DataAPI {

    /**
     * Retrieve a network user instance from the users map if it exists,
     * otherwise retrieve the user instance from the database.
     *
     * @param uuid the uuid of the user
     * @param storeUser does anything
     *
     * @return NetworkUser instance
     */
    @Deprecated
    public static NetworkUser retrieveUser(String uuid, boolean storeUser) {
        NetworkUser user = DAOManager.getNetworkUserDAO().findOne("uuid", uuid);

        if (user == null) {
            return null;
        }

        return user;
    }

    /**
     * Retrieve a network user instance from the users map if it exists,
     * otherwise retrieve the user instance from the database.
     *
     * @param uuid the uuid of the user
     *
     * @return NetworkUser instance
     */
    public static NetworkUser retrieveUser(String uuid) {
        NetworkUser user = DAOManager.getNetworkUserDAO().findOne("uuid", uuid);

        if (user == null) {
            return null;
        }

        return user;
    }

    /**
     * Retrieve a network user instance from the users map by name if it exists,
     * otherwise retrieve the user instance from the database.
     *
     * @param name the name of the user
     * @return NetworkUser instance
     */
    public static NetworkUser retrieveUserByName(String name) {
        for (NetworkUser user : DAOManager.getNetworkUserDAO().createQuery().field("name").containsIgnoreCase(name)) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }

        return null;
    }

    /**
     * Initializes a network user instance for an online player.
     *
     * @param name the name of the user
     * @param uuid the uuid of the user
     * @param ip the ip of the user
     * @return Networkuser instance
     */
    public static NetworkUser initUser(String uuid, String name, String ip) {
        NetworkUser user = DAOManager.getNetworkUserDAO().findOne("uuid", uuid);

        if (user == null) {
            user = new NetworkUser(uuid);
        }

        DataUtil.updateNetworkUser(user, name, ip);

        return user;
    }

    /**
     * Get a user with the specified uuid
     *
     * @param uuid the uuid of the user
     *
     * @return the network user ebean mapped to the specified uuid
     */
    public static NetworkUser getUser(String uuid) {
        return retrieveUser(uuid);
    }

    /**
     * Save a users data. If a bean is dirty (contains changes) or is new
     * it will be saved to the database.
     *
     * @param user the network user ebean of the user
     */
    public static void saveUser(NetworkUser user) {
        DAOManager.getNetworkUserDAO().save(user);
    }


    /**
     * Save a collection of users. If a bean is dirty (contains changes) or is new
     * it will be saved to the database.
     *
     * @param users a collection of network users
     */
    public static void saveMany(Collection<NetworkUser> users) {
        for (NetworkUser user : users) {
            DAOManager.getNetworkUserDAO().save(user);
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
        NetworkUserPurchase purchase = new NetworkUserPurchase(type, data);
        DAOManager.getNetworkUserPurchaseDAO().save(purchase);

        if (save) {
            user.getNetworkUserPurchases().add(purchase);
            DAOManager.getNetworkUserDAO().save(user);
        }
    }

    /**
     * Get the network settings.
     *
     * @return settings network settings
     */
    public static NetworkSettings getNetworkSettings() {
        return DAOManager.getNetworkSettingsDAO().find().get();
    }

    /**
     * Save the network settings.
     */
    public static void saveNetworkSettings(NetworkSettings settings) {
        DAOManager.getNetworkSettingsDAO().save(settings);
    }

}

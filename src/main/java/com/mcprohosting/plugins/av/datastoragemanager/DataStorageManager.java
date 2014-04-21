package com.mcprohosting.plugins.av.datastoragemanager;

import com.mcprohosting.plugins.av.datastoragemanager.database.DAOManager;
import com.mcprohosting.plugins.av.datastoragemanager.database.MongoResource;
import org.bukkit.plugin.java.JavaPlugin;

public class DataStorageManager extends JavaPlugin {

    private static DataStorageManager instance;
    private static MongoResource mongoResource;
    private static DAOManager daoManager;

    public void onEnable() {
        instance = this;
        mongoResource = new MongoResource();
        daoManager = new DAOManager(mongoResource);
    }

    public static DataStorageManager getInstance() {
        return instance;
    }

    public static MongoResource getMongoResource() {
        return mongoResource;
    }

    public static DAOManager getDaoManager() {
        return daoManager;
    }

}

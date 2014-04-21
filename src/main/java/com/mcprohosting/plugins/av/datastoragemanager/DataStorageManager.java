package com.mcprohosting.plugins.av.datastoragemanager;

import com.mcprohosting.plugins.av.datastoragemanager.database.MongoResource;
import org.bukkit.plugin.java.JavaPlugin;

public class DataStorageManager extends JavaPlugin {

    private static DataStorageManager instance;
    private static MongoResource mongoResource;

    public void onEnable() {
        instance = this;
        mongoResource = new MongoResource();
    }

    public static DataStorageManager getInstance() {
        return instance;
    }

    public static MongoResource getMongoResource() {
        return mongoResource;
    }

}

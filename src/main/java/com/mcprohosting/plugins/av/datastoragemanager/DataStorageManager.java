package com.mcprohosting.plugins.av.datastoragemanager;

import com.gmail.favorlock.commonutils.command.CommandController;
import com.mcprohosting.plugins.av.datastoragemanager.commands.DBCommand;
import com.mcprohosting.plugins.av.datastoragemanager.database.DAOManager;
import com.mcprohosting.plugins.av.datastoragemanager.database.MongoResource;
import com.mcprohosting.plugins.av.datastoragemanager.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DataStorageManager extends JavaPlugin {

    private static DataStorageManager instance;
    private static MongoResource mongoResource;
    private static DAOManager daoManager;

    public void onEnable() {
        instance = this;
        mongoResource = new MongoResource();
        daoManager = new DAOManager(mongoResource);

        registerCommands();
        registerListeners();
    }

    public void registerCommands() {
        CommandController.registerCommands(this, new DBCommand());
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
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

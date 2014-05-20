package com.mcprohosting.plugins.av.datastoragemanager;

import com.gmail.favorlock.commonutils.command.CommandController;
import com.mcprohosting.plugins.av.datastoragemanager.commands.DBCommand;
import com.mcprohosting.plugins.av.datastoragemanager.config.Settings;
import com.mcprohosting.plugins.av.datastoragemanager.database.DAOManager;
import com.mcprohosting.plugins.av.datastoragemanager.database.ResourceManager;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.*;
import com.mcprohosting.plugins.av.datastoragemanager.listeners.PlayerListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class DataStorageManager extends JavaPlugin {

    @Getter private static DataStorageManager instance;
    @Getter private static ResourceManager resourceManager;
    @Getter private static DAOManager daoManager;
    @Getter private static Settings settings;

    public void onEnable() {
        instance = this;
        settings = new Settings(this);

        if (settings.initialize(this) == false) {
            disable();
        }

        resourceManager = new ResourceManager();
        daoManager = new DAOManager(resourceManager);

        registerCommands();
        registerListeners();
    }

    public void registerCommands() {
        CommandController.registerCommands(this, new DBCommand());
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    private void disable() {
        Bukkit.getPluginManager().disablePlugin(this);
    }

    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<>();

        list.add(NetworkSettings.class);
        list.add(NetworkUser.class);
        list.add(NetworkUserPurchase.class);

        return list;
    }

}

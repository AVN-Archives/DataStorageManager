package com.mcprohosting.plugins.av.datastoragemanager;

import com.avaje.ebean.SqlRow;
import com.mcprohosting.plugins.av.datastoragemanager.beans.*;
import com.mcprohosting.plugins.av.datastoragemanager.config.AvajeConfiguration;
import com.mcprohosting.plugins.av.datastoragemanager.database.AvajeDatabase;
import com.mcprohosting.plugins.av.datastoragemanager.listeners.PlayerListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class DataStorageManager extends JavaPlugin {

    @Getter private static DataStorageManager instance;
    @Getter private static AvajeConfiguration avajeConfiguration;
    @Getter private static AvajeDatabase avajeDatabase;

    public void onEnable() {
        instance = this;
        avajeConfiguration = new AvajeConfiguration();

        loadSQL();
        registerListeners();
    }

    public void loadSQL() {
        avajeDatabase = new AvajeDatabase(this, getClassLoader());

        String sql = "select count(*) as count from dual";
        SqlRow row = DataStorageManager.getAvajeDatabase().getServer().createSqlQuery(sql).findUnique();

        Integer i = row.getInteger("count");

        getLogger().info("Got " + i + " - DataSource good.");

        if (i != null) {
            postloadSQL();
        }
    }

    public void postloadSQL() {
        if (avajeConfiguration.ebean_ddl_generate || avajeConfiguration.ebean_ddl_run) {
            avajeConfiguration.ebean_ddl_generate = false;
            avajeConfiguration.ebean_ddl_run = false;

            saveConfiguration();
        }
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> result = new ArrayList<>();
        result.add(NetworkUser.class);
        result.add(NetworkAchievement.class);
        result.add(NetworkUserPreferences.class);
        result.add(NetworkUserModeration.class);
        result.add(NetworkUserPurchase.class);
        result.add(NetworkUserAchievement.class);
        return result;
    }

    public void saveConfiguration() {
        try {
            avajeConfiguration.save();
        } catch (InvalidConfigurationException e) {
            getLogger().warning("Failed to save the config!");
        }
    }

}

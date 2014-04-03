package com.mcprohosting.plugins.av.datastoragemanager;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import com.mcprohosting.plugins.av.datastoragemanager.beans.User;
import com.mcprohosting.plugins.av.datastoragemanager.config.AvajeConfiguration;
import com.mcprohosting.plugins.av.datastoragemanager.database.AvajeDatabase;
import com.mcprohosting.plugins.av.datastoragemanager.listeners.PlayerListener;
import lombok.Getter;
import org.bukkit.Bukkit;
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
        SqlRow row = Ebean.createSqlQuery(sql).findUnique();

        Integer i = row.getInteger("count");

        getLogger().info("Got " + i + " - DataSource good.");
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> result = new ArrayList<>();
        result.add(User.class);
        return result;
    }

}

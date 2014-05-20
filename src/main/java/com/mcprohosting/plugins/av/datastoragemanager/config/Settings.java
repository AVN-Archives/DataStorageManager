package com.mcprohosting.plugins.av.datastoragemanager.config;

import com.gmail.favorlock.commonutils.configuration.ConfigModel;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class Settings extends ConfigModel {

    public Settings(Plugin plugin) {
        CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
    }

    public boolean initialize(Plugin plugin) {
        try {
            this.init();
            return true;
        } catch (InvalidConfigurationException e) {
            plugin.getLogger().warning("Unable to load the configuration file!");
        }

        return false;
    }

    public String host = "127.0.0.1";
    public int port = 27017;
    public String username = "";
    public String password = "";
    public String database = "antvenomnetwork";

}

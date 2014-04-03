package com.mcprohosting.plugins.av.datastoragemanager.config;

import com.gmail.favorlock.commonutils.configuration.ConfigModel;
import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;

public class AvajeConfiguration extends ConfigModel {

    public AvajeConfiguration() {
        CONFIG_FILE = new File(DataStorageManager.getInstance().getDataFolder(), "avaje.yml");
        CONFIG_HEADER = "Avaje Configuration Settings";

        initAvajeConfig();
    }

    public void initAvajeConfig() {
        try {
            init();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public boolean ebean_ddl_generate = false;
    public boolean ebean_ddl_run = false;
    public boolean ebean_debug_sql = true;
    public boolean ebean_debug_lazyload = false;

    public String ebean_logging_logging = "all";
    public String ebean_logging_logfilesharing = "all";
    public String ebean_logging_directory = "logs";
    public String ebean_logging_iud = "sql";
    public String ebean_logging_query = "sql";
    public String ebean_logging_sqlquery = "sql";

    public String ebean_datasource_mysql_username = "minecraft";
    public String ebean_datasource_mysql_password = "1234";
    public String ebean_datasource_mysql_databaseUrl = "jdbc:mysql://127.0.0.1:3306/minecraft";
    public String ebean_datasource_mysql_databaseDriver = "com.mysql.jdbc.Driver";
    public Integer ebean_datasource_mysql_minConnections = 1;
    public Integer ebean_datasource_mysql_maxConnections = 25;
    public String ebean_datasource_mysql_isolationlevel = "read_commited";

}

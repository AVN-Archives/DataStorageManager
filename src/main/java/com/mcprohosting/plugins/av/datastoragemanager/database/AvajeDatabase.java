package com.mcprohosting.plugins.av.datastoragemanager.database;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import com.mcprohosting.plugins.av.datastoragemanager.config.AvajeConfiguration;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class AvajeDatabase {

    @Getter EbeanServer server;

    public AvajeDatabase(JavaPlugin plugin, ClassLoader classLoader) {
        if (classLoader == null) {
            throw new IllegalArgumentException("classloader cannot be null");
        }

        init(plugin, classLoader);
    }

    private void init(JavaPlugin plugin, ClassLoader classLoader) {
        ServerConfig config = new ServerConfig();
        config.setName(plugin.getName());
        config.setDataSourceConfig(constructDataSourceConfig());
        config.setClasses(plugin.getDatabaseClasses());

        configureServerConfig(config);

        ClassLoader previous = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);

        server = EbeanServerFactory.create(config);

        Thread.currentThread().setContextClassLoader(previous);
    }

    private DataSourceConfig constructDataSourceConfig() {
        AvajeConfiguration avajeConfiguration = DataStorageManager.getAvajeConfiguration();

        DataSourceConfig config = new DataSourceConfig();
        config.setDriver(avajeConfiguration.ebean_datasource_mysql_databaseDriver);
        config.setUsername(avajeConfiguration.ebean_datasource_mysql_username);
        config.setPassword(avajeConfiguration.ebean_datasource_mysql_password);
        config.setUrl(avajeConfiguration.ebean_datasource_mysql_databaseUrl);

        return config;
    }

    private void configureServerConfig(ServerConfig config) {
        AvajeConfiguration avajeConfiguration = DataStorageManager.getAvajeConfiguration();

        config.setDdlGenerate(avajeConfiguration.ebean_ddl_generate);
        config.setDdlRun(avajeConfiguration.ebean_ddl_run);

        config.setDefaultServer(true);
        config.setRegister(true);
    }

}

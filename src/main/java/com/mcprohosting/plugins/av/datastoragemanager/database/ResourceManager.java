package com.mcprohosting.plugins.av.datastoragemanager.database;

import com.gmail.favorlock.quickmongo.DatabaseObject;
import com.gmail.favorlock.quickmongo.MongoResource;
import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import com.mcprohosting.plugins.av.datastoragemanager.config.Settings;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.io.*;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class ResourceManager {
    @Getter private MongoResource resource;

    public ResourceManager() {
        if (resource == null) {
            resource = new MongoResource(getDatabaseObject(), DataStorageManager.getInstance().getLogger());
        }
    }

    private DatabaseObject getDatabaseObject() {
        Settings settings = DataStorageManager.getSettings();

        DatabaseObject databaseObject = new DatabaseObject(settings.host, settings.port);

        if (settings.username.equals("") == false && settings.password.equals("") == false) {
            databaseObject.withUsername(settings.username);
            databaseObject.withPassword(settings.password);
        }

        if (settings.database.equals("") == false) {
            databaseObject.withDatabase(settings.database);
        }

        return databaseObject;
    }
}

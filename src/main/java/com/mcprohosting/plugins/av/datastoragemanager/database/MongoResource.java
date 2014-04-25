package com.mcprohosting.plugins.av.datastoragemanager.database;

import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.bukkit.Bukkit;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.io.*;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class MongoResource {
    private MongoClient mongoClient;
    private Properties properties;

    public MongoResource() {
        DataStorageManager.getInstance().saveResource("mongodb.properties", false);

        try {
            if (properties == null) {
                try {
                    properties = loadProperties();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (mongoClient == null) {
                mongoClient = getClient();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(new File(DataStorageManager.getInstance().getDataFolder(), "mongodb.properties"));

        if (inputStream == null) {
            throw new FileNotFoundException("Unable to load mongodb.properties!");
        }

        properties.load(inputStream);
        return properties;
    }

    private MongoClient getClient() {
        try {
            if (!properties.getProperty("username").isEmpty() &&
                    !properties.getProperty("password").isEmpty()) {
                MongoCredential credential = MongoCredential.createMongoCRCredential(
                        properties.getProperty("username"),
                        getDatabase(properties),
                        properties.getProperty("password").toCharArray()
                );
                return new MongoClient(new ServerAddress(
                        properties.getProperty("host"),
                        Integer.valueOf(properties.getProperty("port"))), Arrays.asList(credential));
            } else {
                return new MongoClient(
                        properties.getProperty("host"),
                        Integer.valueOf(properties.getProperty("port")));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Datastore getDatastore() {
        Datastore datastore;

        datastore = new Morphia((Set) new HashSet<Class<?>>(DataStorageManager.getInstance().getDatabaseClasses())).
                createDatastore(mongoClient, getDatabase(properties));
        if (!properties.getProperty("username").isEmpty() &&
                !properties.getProperty("password").isEmpty()) {
            datastore.getDB().authenticateCommand(properties.getProperty("username"), properties.getProperty("password").toCharArray());
        }

        return datastore;
    }

    public static String getDatabase(Properties properties) {
        return properties.getProperty("database").isEmpty() ? "antvenomnetwork" : properties.getProperty("database");
    }
}

package com.mcprohosting.plugins.av.datastoragemanager.database;

import com.mcprohosting.plugins.av.datastoragemanager.DataStorageManager;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class MongoResource {
    private static MongoResource instance;

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

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = DataStorageManager.getInstance().getResource("mongodb.properties");

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
                        properties.getProperty("username"), "antvenomnetwork",
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
                createDatastore(mongoClient, "antvenomnetwork");

        return datastore;
    }

    public static MongoResource getInstance() {
        return instance;
    }
}

package org.example.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DBConnection {

    private static DBConnection instance;
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    private DBConnection() {
        mongoClient = MongoClients.create("mongodb://root:password@localhost:27017");
        database = mongoClient.getDatabase("ztp");
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void close() {
        mongoClient.close();
    }

}

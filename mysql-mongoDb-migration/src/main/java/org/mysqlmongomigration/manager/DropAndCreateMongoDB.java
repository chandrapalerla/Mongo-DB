package org.mysqlmongomigration.manager;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.mysqlmongomigration.DBConstants;

public class DropAndCreateMongoDB {

    public static void DropAndCreatedMondoDataBase() {
        try (MongoClient mongoClient = MongoClients.create(DBConstants.MONGO_URI)) {
            // Get a list of database names
            MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();

            // Check if the database exists and drop it
            for (String name : databaseNames) {
                if (name.equals(DBConstants.MONGO_DB_NAME)) {
                    mongoClient.getDatabase(DBConstants.MONGO_DB_NAME).drop();
                    System.out.println("Database " + DBConstants.MONGO_DB_NAME + " dropped.");
                    break;
                }
            }

            // Create a new database (just by getting it, MongoDB will create it when you start using it)
            MongoDatabase newDatabase = mongoClient.getDatabase(DBConstants.MONGO_DB_NAME);
            System.out.println("New database " + DBConstants.MONGO_DB_NAME + " created.");

            // Now you can start creating collections and inserting documents into the new database
            newDatabase.createCollection("myCollection");
            System.out.println("Collection myCollection created in database " + DBConstants.MONGO_DB_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


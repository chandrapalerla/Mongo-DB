package org.mysqlmongomigration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.sql.*;

public class MySQLToMongoDBMigration {

    public static void main(String[] args) {

        // MongoDB connection
        String mongoUrl = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(mongoUrl);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("pet");

        try (Connection mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet1",
                DBConstants.MYSQL_USER, DBConstants.MYSQL_PASSWORD)) {
            DatabaseMetaData metaData = mysqlConnection.getMetaData();
            Statement statement = mysqlConnection.createStatement();

            // Get all table names
            ResultSet tables = metaData.getTables("pet1", null, "%", new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Migrating table: " + tableName);
                migrateTable(tableName, statement, metaData, mongoDatabase);
            }

            System.out.println("Migration completed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    private static void migrateTable(String tableName, Statement statement, DatabaseMetaData metaData, MongoDatabase mongoDatabase) throws Exception {
        ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
        MongoCollection<Document> collection = mongoDatabase.getCollection(tableName.toLowerCase());

        // Insert data into MongoDB
        while (rs.next()) {
            Document doc = new Document();
            ResultSet columns = metaData.getColumns("pet1", null, tableName, null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if (!columnName.equalsIgnoreCase("id")) {
                    doc.append(columnName, rs.getObject(columnName));
                }
            }
            collection.insertOne(doc);

            // Handle foreign keys dynamically
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
            while (foreignKeys.next()) {
                String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
                String pkTableName = foreignKeys.getString("PKTABLE_NAME");
                String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");

                Integer fkValue = rs.getInt(fkColumnName);
                MongoCollection<Document> relatedCollection = mongoDatabase.getCollection(pkTableName.toLowerCase());
                Document relatedDoc = relatedCollection.find(Filters.eq("_id", fkValue)).first();

                if (relatedDoc != null) {
                    doc.append(fkColumnName, relatedDoc.getObjectId("_id").toHexString());
                }
            }

            collection.updateOne(new Document("_id", doc.getObjectId("_id")), new Document("$set", doc));
        }

        // Handle indexes
        ResultSet indexes = metaData.getIndexInfo(null, null, tableName, false, false);
        while (indexes.next()) {
            String indexName = indexes.getString("INDEX_NAME");
            boolean nonUnique = indexes.getBoolean("NON_UNIQUE");
            String columnName = indexes.getString("COLUMN_NAME");

            if (indexName != null && columnName != null) {
                collection.createIndex(new Document(columnName, 1), new com.mongodb.client.model.IndexOptions().name(indexName).unique(!nonUnique));
            }
        }
    }
}

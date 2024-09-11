package org.mysqlmongomigration.manager;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.mysqlmongomigration.DBConstants;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

public class ForeignKeysMigration {
    public static void migrateForeignKeys(Connection mysqlConnection, MongoDatabase mongoDatabase) {
        try {
            Statement statement = mysqlConnection.createStatement();
            DatabaseMetaData metaData = mysqlConnection.getMetaData();
            ResultSet tables = metaData.getTables(DBConstants.CATALOG, null, DBConstants.TABLE_NAME_PATTERN, new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString(DBConstants.TABLE_NAME);
                ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
                while (foreignKeys.next()) {
                    String fkColumnName = foreignKeys.getString(DBConstants.FK_COLUMN_NAME);
                    String pkTableName = foreignKeys.getString(DBConstants.PK_TABLE_NAME);
                    String pkColumnName = foreignKeys.getString(DBConstants.PK_COLUMN_NAME);

                    String petId = tableName.substring(0, tableName.length() - 1).concat("_id");
                    ResultSet fkResultSet = statement.executeQuery("SELECT " + petId + " as id, " + fkColumnName + " FROM " + tableName);
                    while (fkResultSet.next()) {
                        int fkValue = fkResultSet.getInt(fkColumnName);
                        MongoCollection<Document> pkCollection = mongoDatabase.getCollection(pkTableName);
                        Document relatedDoc = pkCollection.find(Filters.eq(pkColumnName, fkValue)).first();

                        MongoCollection<Document> collection = mongoDatabase.getCollection(tableName);
                        collection.updateOne(new Document(petId, fkResultSet.getInt("id")),
                                new Document("$set", new Document(fkColumnName, relatedDoc != null ? relatedDoc.get("_id") : null)));
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
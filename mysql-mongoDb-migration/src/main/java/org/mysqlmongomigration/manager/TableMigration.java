package org.mysqlmongomigration.manager;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mysqlmongomigration.DBConstants;

import java.sql.*;

public class TableMigration {
    public static void migrateTableToDocument(Connection mysqlConnection, MongoDatabase mongoDatabase, String tableName) {
        try {
            Statement statement = mysqlConnection.createStatement();
            ResultSet rs = statement.executeQuery(DBConstants.SELECT_QUERY + tableName);

            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            MongoCollection<Document> collection = mongoDatabase.getCollection(tableName);

            while (rs.next()) {
                Document document = new Document();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rsMetaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    document.append(columnName, value);
                }
                collection.insertOne(document);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

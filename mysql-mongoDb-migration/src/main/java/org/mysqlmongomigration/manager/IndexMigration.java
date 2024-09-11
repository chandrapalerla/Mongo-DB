package org.mysqlmongomigration.manager;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import org.mysqlmongomigration.DBConstants;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndexMigration {
    public static void migrateIndexes(Connection mysqlConnection, MongoDatabase mongoDatabase, String tableName) {
        try {
            DatabaseMetaData metaData = mysqlConnection.getMetaData();
            ResultSet indexInfo = metaData.getIndexInfo(null, null, tableName, false, false);

            MongoCollection<Document> collection = mongoDatabase.getCollection(tableName);

            while (indexInfo.next()) {
                String indexName = indexInfo.getString(DBConstants.INDEX_NAME);
                boolean nonUnique = indexInfo.getBoolean(DBConstants.NON_UNIQUE);
                String columnName = indexInfo.getString(DBConstants.COLUMN_NAME);

                if (indexName != null && columnName != null) {
                    Document indexDocument = new Document(columnName, 1);
                    IndexOptions indexOptions = new IndexOptions().name(indexName).unique(!nonUnique);
                    collection.createIndex(indexDocument, indexOptions);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

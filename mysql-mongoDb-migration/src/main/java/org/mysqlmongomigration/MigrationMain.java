package org.mysqlmongomigration;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.mysqlmongomigration.manager.DropAndCreateMongoDB;
import org.mysqlmongomigration.manager.ForeignKeysMigration;
import org.mysqlmongomigration.manager.IndexMigration;
import org.mysqlmongomigration.manager.TableMigration;

import java.sql.*;

public class MigrationMain {
    public static void main(String[] args) {

        //DropAndCreateMongoDB.DropAndCreatedMondoDataBase();

        try (Connection mysqlConnection = DriverManager.getConnection(DBConstants.MYSQL_URL,
                DBConstants.MYSQL_USER, DBConstants.MYSQL_PASSWORD);
             com.mongodb.client.MongoClient mongoClient = MongoClients.create(DBConstants.MONGO_URI)) {

            //dynamically retrieve all table names from the MySQL database.
            DatabaseMetaData metaData = mysqlConnection.getMetaData();
            // Retrieve the tables in the specific database
            ResultSet tables = metaData.getTables(DBConstants.CATALOG, null, "%", new String[]{"TABLE"});

            MongoDatabase mongoDatabase = mongoClient.getDatabase(DBConstants.MONGO_DB_NAME);

            while (tables.next()) {
                String tableName = tables.getString(DBConstants.TABLE_NAME);
                TableMigration.migrateTableToDocument(mysqlConnection, mongoDatabase, tableName);
                IndexMigration.migrateIndexes(mysqlConnection, mongoDatabase, tableName);
                System.out.println("tableName::::" + tableName);
            }
            ForeignKeysMigration.migrateForeignKeys(mysqlConnection, mongoDatabase);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
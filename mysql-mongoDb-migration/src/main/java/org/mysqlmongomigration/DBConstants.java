package org.mysqlmongomigration;

public class DBConstants {
    // MySQL connection details
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/pet";
    public static final String MYSQL_USER = "root";
    public static final String MYSQL_PASSWORD = "chandu95";
    public static final String CATALOG = "pet";

    public static final String SELECT_QUERY = "SELECT * FROM ";
    public static final String INDEX_NAME = "INDEX_NAME";
    public static final String NON_UNIQUE = "NON_UNIQUE";
    public static final String COLUMN_NAME = "COLUMN_NAME";
    public static final String TABLE_NAME = "TABLE_NAME";

    // MongoDB connection details
    public static final String MONGO_URI = "mongodb://localhost:27017";
    public static final String MONGO_DB_NAME = "pet";

    public static final String TABLE_NAME_PATTERN = "%";
    public static final String FK_COLUMN_NAME = "FKCOLUMN_NAME";
    public static final String PK_TABLE_NAME = "PKTABLE_NAME";
    public static final String PK_COLUMN_NAME = "PKCOLUMN_NAME";
}

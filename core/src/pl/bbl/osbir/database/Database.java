package pl.bbl.osbir.database;

import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.database.connection.DatabaseCredentials;
import pl.bbl.osbir.database.properties.DatabaseProperties;


public class Database {
    public static DatabaseConnection establishDatabaseConnection(){
        return new DatabaseConnection(createCredentials());
    }

    private static DatabaseCredentials createCredentials(){
        return new DatabaseCredentials(DatabaseProperties.DATABASE_HOST, DatabaseProperties.DATABASE_PORT,
                DatabaseProperties.DATABASE_NAME, DatabaseProperties.DATABASE_USER, DatabaseProperties.DATABASE_PASSWORD);
    }
}

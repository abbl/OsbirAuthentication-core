package pl.bbl.database;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.database.connection.DatabaseCredentials;
import pl.bbl.servers.ServersMutualProperties;


public class Database {
    public static DatabaseConnection establishDatabaseConnection(){
        return new DatabaseConnection(createCredentials());
    }

    private static DatabaseCredentials createCredentials(){
        return new DatabaseCredentials(ServersMutualProperties.DATABASE_HOST, ServersMutualProperties.DATABASE_PORT,
                ServersMutualProperties.DATABASE_NAME, ServersMutualProperties.DATABASE_USER, ServersMutualProperties.DATABASE_PASSWORD);
    }
}

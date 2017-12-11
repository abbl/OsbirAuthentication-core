package pl.bbl.database.tools;

import pl.bbl.database.connection.DatabaseConnection;

public class DatabaseTools {

    public static boolean doesConnectionObjectQualifyForChange(DatabaseConnection firstConnection, DatabaseConnection secondConnection){
        return !isDatabaseConnectionEqual(firstConnection, secondConnection) || isDatabaseNull(firstConnection);
    }

    public static boolean isDatabaseConnectionEqual(DatabaseConnection firstConnection, DatabaseConnection secondConnection){
        if(firstConnection != null)
            return firstConnection.equals(secondConnection);
        return false;
    }

    public static boolean isDatabaseNull(DatabaseConnection databaseConnection){
        return databaseConnection != null;
    }
}

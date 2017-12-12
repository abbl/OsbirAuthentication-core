package pl.bbl.features.authentication.gameserver;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.database.tools.DatabaseTools;

public class GameServerAuthenticator {
    private static DatabaseConnection databaseConnection;

    public GameServerAuthenticator(DatabaseConnection databaseConnection){
        if(DatabaseTools.doesConnectionObjectQualifyForChange(GameServerAuthenticator.databaseConnection, databaseConnection))
            GameServerAuthenticator.databaseConnection = databaseConnection;
    }

    public static void authenticateGameServer(){
        
    }
}

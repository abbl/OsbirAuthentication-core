package pl.bbl.osbir;

import pl.bbl.osbir.database.Database;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.servers.user.UserAuthenticationServer;


public class SegmentCommunicationDirector {
    private DatabaseConnection databaseConnection;
    private UserAuthenticationServer userAuthenticationServer;

    public SegmentCommunicationDirector(){
        establishDatabaseConnection();
        userAuthenticationServer = new UserAuthenticationServer(databaseConnection);
        userAuthenticationServer.start();
    }

    private void establishDatabaseConnection(){
        databaseConnection = Database.establishDatabaseConnection();
    }

}

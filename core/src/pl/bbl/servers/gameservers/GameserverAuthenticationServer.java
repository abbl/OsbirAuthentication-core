package pl.bbl.servers.gameservers;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.network.server.BasicServer;
import pl.bbl.network.server.connection.AbstractUser;

public class GameserverAuthenticationServer extends BasicServer{
    private DatabaseConnection databaseConnection;

    public GameserverAuthenticationServer(int port, AbstractUser abstractUser, DatabaseConnection databaseConnection) {
        super(port, abstractUser);
        this.databaseConnection = databaseConnection;
    }

}

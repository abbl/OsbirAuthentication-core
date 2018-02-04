package pl.bbl.osbir;

import pl.bbl.osbir.database.Database;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.servers.gameserver.GameServerAuthenticationServer;
import pl.bbl.osbir.servers.gameserver.user.GameServer;
import pl.bbl.osbir.servers.user.UserAuthenticationServer;

import java.util.ArrayList;
import java.util.HashMap;


public class SegmentCommunicationDirector {
    private DatabaseConnection databaseConnection;
    private UserAuthenticationServer userAuthenticationServer;
    private GameServerAuthenticationServer gameServerAuthenticationServer;

    public SegmentCommunicationDirector(){
        establishDatabaseConnection();
        userAuthenticationServer = new UserAuthenticationServer(databaseConnection, this);
        userAuthenticationServer.start();
        gameServerAuthenticationServer = new GameServerAuthenticationServer(databaseConnection, this);
        gameServerAuthenticationServer.start();
    }

    private void establishDatabaseConnection(){
        databaseConnection = Database.establishDatabaseConnection();
    }

    public ArrayList<HashMap<String,String>> getGameServerList() {
        return gameServerAuthenticationServer.getGameServerList();
    }

    public void verifyUser(String userKey, String username, GameServer gameServer) {
        userAuthenticationServer.verifyUserForGameServer(userKey, username, gameServer);
    }
}

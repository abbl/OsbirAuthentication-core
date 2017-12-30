package pl.bbl.osbir.servers.gameservers;

import pl.bbl.osbir.SegmentCommunicationDirector;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.servers.gameservers.gameserver.GameServer;
import pl.bbl.osbir.servers.gameservers.instance.GameServerAuthenticationServer;
import pl.bbl.osbir.servers.gameservers.properties.GameServerProperties;
import pl.bbl.osbir.tools.logger.ServerLogger;

import java.util.List;

public class GameServerAuthenticationWrapper {
    private DatabaseConnection databaseConnection;
    private SegmentCommunicationDirector segmentCommunicationDirector;
    private GameServerAuthenticationServer gameServerAuthenticationServer;
    private Thread gameServerAuthenticationThread;

    public GameServerAuthenticationWrapper(DatabaseConnection databaseConnection, SegmentCommunicationDirector segmentCommunicationDirector){
        this.databaseConnection = databaseConnection;
        this.segmentCommunicationDirector = segmentCommunicationDirector;
        initializeServer();
    }

    private void initializeServer(){
        gameServerAuthenticationServer = new GameServerAuthenticationServer(GameServerProperties.GAMESERVER_CONNECTION_PORT,
                GameServer.class, databaseConnection, this);
        gameServerAuthenticationThread = new Thread(gameServerAuthenticationServer);
    }

    public void startServer() {
        gameServerAuthenticationThread.start();
        ServerLogger.log("GameServerAuthentication started.");
    }

    public void requestUserIdVerification(String gameServerId, String userId) {
        segmentCommunicationDirector.requestUserIdVerification(gameServerId, userId);
    }

    public void passUserIdVerificationResult(String gameServerId, String userId, boolean result){
        gameServerAuthenticationServer.passUserIdVerificationResult(gameServerId, userId, result);
}

    public List<GameServer> getGameServerList() {
        return gameServerAuthenticationServer.getGameServerList();
    }


}

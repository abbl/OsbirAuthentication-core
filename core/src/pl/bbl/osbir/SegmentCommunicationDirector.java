package pl.bbl.osbir;

import pl.bbl.osbir.database.Database;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.servers.gameservers.GameServerAuthenticationWrapper;
import pl.bbl.osbir.servers.users.UserAuthenticationWrapper;
import pl.bbl.osbir.tools.logger.ServerLogger;


public class SegmentCommunicationDirector {
    private GameServerAuthenticationWrapper gameServerAuthenticationWrapper;
    private UserAuthenticationWrapper userAuthenticationWrapper;
    private DatabaseConnection databaseConnection;

    public SegmentCommunicationDirector(){
        establishConnectionToDatabase();
        gameServerAuthenticationWrapper = new GameServerAuthenticationWrapper(databaseConnection, this);
        userAuthenticationWrapper = new UserAuthenticationWrapper(databaseConnection, this);
    }

    private void establishConnectionToDatabase(){
        databaseConnection = Database.establishDatabaseConnection();
    }

    public void startUserAuthenticationServer(){
        if(databaseConnection != null)
            userAuthenticationWrapper.startServer();
        else
            ServerLogger.log("UserAuthentication won't be started because database connection is invalid.");
    }

    public void startGameServerAuthenticationServer(){
        if(databaseConnection != null)
            gameServerAuthenticationWrapper.startServer();
        else
            ServerLogger.log("GameServerAuthentication won't be started because database connection is invalid.");
    }

    public void requestUserIdVerification(String gameServerId, String userId){
        userAuthenticationWrapper.requestUserIdVerification(gameServerId, userId);
    }

    public void passUserIdVerificationResult(String gameServerId, String userId, boolean result){
        gameServerAuthenticationWrapper.passUserIdVerificationResult(gameServerId, userId, result);
    }

    public void getGameServerList(){
        gameServerAuthenticationWrapper.getGameServerList();
    }
}

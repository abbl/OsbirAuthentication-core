package pl.bbl.osbir.servers.users;

import pl.bbl.osbir.SegmentCommunicationDirector;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.servers.users.instance.UserAuthenticationServer;
import pl.bbl.osbir.servers.users.properties.UserServerProperties;
import pl.bbl.osbir.servers.users.user.User;

public class UserAuthenticationWrapper {
    private SegmentCommunicationDirector segmentCommunicationDirector;
    private UserAuthenticationServer userAuthenticationServer;
    private Thread userAuthenticationThread;

    public UserAuthenticationWrapper(DatabaseConnection databaseConnection, SegmentCommunicationDirector segmentCommunicationDirector){
        this.segmentCommunicationDirector = segmentCommunicationDirector;
        initializeServer(databaseConnection);
    }

    private void initializeServer(DatabaseConnection databaseConnection){
        userAuthenticationServer = new UserAuthenticationServer(UserServerProperties.USER_CONNECTION_PORT, User.class, databaseConnection, this);
        userAuthenticationThread = new Thread(userAuthenticationServer);
    }

    public void startServer() {
        userAuthenticationThread.start();
    }

    public void requestUserIdVerification(String gameServerId, String userId) {
        userAuthenticationServer.verifyUserId(gameServerId, userId);
    }

    public void passUserIdVerificationResult(String gameServerId, String userId, boolean result) {
        segmentCommunicationDirector.passUserIdVerificationResult(gameServerId, userId, result);
    }
}

package pl.bbl.osbir.servers.users.instance;

import io.netty.channel.ChannelPipeline;

import pl.bbl.osbir.SegmentCommunicationDirector;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.osbir.servers.users.UserAuthenticationWrapper;
import pl.bbl.osbir.servers.users.receivers.UserAuthenticationReceivers;
import pl.bbl.osbir.servers.users.user.User;


public class UserAuthenticationServer extends AbstractServer {
    private DatabaseConnection databaseConnection;
    private UserAuthenticationWrapper authenticationWrapper;

    public UserAuthenticationServer(int port, Class className, DatabaseConnection databaseConnection, UserAuthenticationWrapper authenticationWrapper) {
        super(port, className);
        this.databaseConnection = databaseConnection;
        this.authenticationWrapper = authenticationWrapper;
    }

    @Override
    protected AbstractUser addHandlersToChannel(ChannelPipeline pipeline) {
        User user = (User) super.addHandlersToChannel(pipeline);
        UserAuthenticationReceivers.addReceivers(user, databaseConnection);
        return user;
    }

    public void verifyUserId(String gameServerId, String userId) {
        boolean result = false;

        if(userHive.getUserById(userId) != null)
            result = true;
        authenticationWrapper.passUserIdVerificationResult(gameServerId, userId, result);
    }
}

package pl.bbl.servers.users;

import io.netty.channel.ChannelPipeline;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.features.authentication.user.handler.UserAuthenticationHandler;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.servers.users.user.User;

public class UserAuthenticationServer extends AbstractServer {
    private DatabaseConnection databaseConnection;

    public UserAuthenticationServer(int port, Class className, DatabaseConnection databaseConnection) {
        super(port, className);
        this.databaseConnection = databaseConnection;
    }

    @Override
    protected AbstractUser addHandlersToChannel(ChannelPipeline pipeline) {
        User user = (User) super.addHandlersToChannel(pipeline);
        pipeline.addLast(new UserAuthenticationHandler(user, databaseConnection));
        return user;
    }
}

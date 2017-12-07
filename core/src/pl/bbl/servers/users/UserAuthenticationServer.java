package pl.bbl.servers.users;

import io.netty.channel.ChannelPipeline;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.servers.users.user.User;

public class UserAuthenticationServer extends AbstractServer {
    private DatabaseConnection databaseConnection;

    public UserAuthenticationServer(int port, AbstractUser abstractUser, DatabaseConnection databaseConnection) {
        super(port, abstractUser);
        this.databaseConnection = databaseConnection;
    }

    @Override
    protected AbstractUser addHandlersToChannel(ChannelPipeline pipeline) {
        User user = (User) userHive.createUser(pipeline.channel());
        return user;
    }
}

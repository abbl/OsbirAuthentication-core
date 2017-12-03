package pl.bbl.servers.users;

import io.netty.channel.ChannelPipeline;
import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.database.connection.DatabaseCredentials;
import pl.bbl.features.authentication.user.handler.UserAuthenticationHandler;
import pl.bbl.network.server.BasicServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.servers.users.properties.ServerProperties;
import pl.bbl.servers.users.user.User;

public class UserAuthenticationServer extends BasicServer {
    private DatabaseConnection databaseConnection;

    public UserAuthenticationServer(int port, AbstractUser abstractUser) {
        super(port, abstractUser);
        establishDatabaseConnection();
    }

    /**
     * WORK THIS ONE OUT
     * XAXAXAXAXAXAX
     * DONT FORGET IT WILL FAIL WITH COMPILATION ANYWAY
     * HELLO FUTURE ME.
     */
    @Override
    protected void addHandlersToChannel(ChannelPipeline pipeline) {
        User user;
        pipeline.addLast(new UserAuthenticationHandler(userHive, databaseConnection));
    }

    private void establishDatabaseConnection(){
        databaseConnection = new DatabaseConnection(createCredentials());
    }

    private DatabaseCredentials createCredentials(){
        return new DatabaseCredentials(ServerProperties.DATABASE_HOST, ServerProperties.DATABASE_PORT,
                ServerProperties.DATABASE_NAME, ServerProperties.DATABASE_USER, ServerProperties.DATABASE_PASSWORD);
    }
}

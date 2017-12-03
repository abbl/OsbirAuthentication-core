package pl.bbl.server;

import io.netty.channel.ChannelPipeline;
import pl.bbl.database.DatabaseConnection;
import pl.bbl.database.DatabaseCredentials;
import pl.bbl.network.server.BasicServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.server.properties.ServerProperties;

public class AuthenticationServer extends BasicServer {
    private DatabaseConnection databaseConnection;

    public AuthenticationServer(int port, AbstractUser abstractUser) {
        super(port, abstractUser);
        establishDatabaseConnection();
    }

    @Override
    protected void addHandlersToChannel(ChannelPipeline pipeline) {

    }

    private void establishDatabaseConnection(){
        databaseConnection = new DatabaseConnection(createCredentials());
    }

    private DatabaseCredentials createCredentials(){
        return new DatabaseCredentials(ServerProperties.DATABASE_HOST, ServerProperties.DATABASE_PORT,
                ServerProperties.DATABASE_NAME, ServerProperties.DATABASE_USER, ServerProperties.DATABASE_PASSWORD);
    }
}

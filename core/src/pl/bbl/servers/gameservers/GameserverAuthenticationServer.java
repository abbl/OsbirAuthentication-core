package pl.bbl.servers.gameservers;

import io.netty.channel.ChannelPipeline;
import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;

public class GameServerAuthenticationServer extends AbstractServer{
    private DatabaseConnection databaseConnection;

    public GameServerAuthenticationServer(int port, Class className, DatabaseConnection databaseConnection) {
        super(port, className);
        this.databaseConnection = databaseConnection;
    }

    @Override
    protected AbstractUser addHandlersToChannel(ChannelPipeline pipeline) {
        return super.addHandlersToChannel(pipeline);
    }
}

package pl.bbl.servers.gameservers;

import io.netty.channel.ChannelPipeline;
import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.features.route.gameserver.authentication.receiver.GameServerAuthenticationReceiver;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.servers.LocalServersDataExchanger;
import pl.bbl.servers.gameservers.gameserver.GameServer;

public class GameServerAuthenticationServer extends AbstractServer{
    private DatabaseConnection databaseConnection;
    private LocalServersDataExchanger localServersDataExchanger;

    public GameServerAuthenticationServer(int port, Class className, DatabaseConnection databaseConnection) {
        super(port, className);
        this.databaseConnection = databaseConnection;
    }

    @Override
    protected AbstractUser addHandlersToChannel(ChannelPipeline pipeline) {
        GameServer gameServer = (GameServer) super.addHandlersToChannel(pipeline);
        PacketHandler packetHandler = new PacketHandler();

        pipeline.addLast(packetHandler);
        packetHandler.addReceiver(new GameServerAuthenticationReceiver("AUTHENTICATION_PACKETS", gameServer, databaseConnection));
        return gameServer;
    }

    public void setLocalServersDataExchanger(LocalServersDataExchanger localServersDataExchanger) {
        this.localServersDataExchanger = localServersDataExchanger;
    }

    public UserHive getUserHive(){
        return userHive;
    }
}

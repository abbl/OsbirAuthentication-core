package pl.bbl.osbir.servers.gameserver;

import pl.bbl.network.server.Server;
import pl.bbl.network.server.handler.PacketDistributor;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.servers.user.properties.UserAuthenticationServerProperties;
import pl.bbl.osbir.servers.user.user.User;
import pl.bbl.osbir.tools.logger.ServerLogger;

public class GameServerAuthenticationServer {
    private Server gameServerAuthenticationServer;
    private Thread serverThread;
    private PacketDistributor packetDistributor;
    private DatabaseConnection databaseConnection;

    public GameServerAuthenticationServer(DatabaseConnection databaseConnection){
        this.packetDistributor = new PacketDistributor();
        this.gameServerAuthenticationServer = new Server(UserAuthenticationServerProperties.PORT, User.class, packetDistributor);
        this.serverThread = new Thread(gameServerAuthenticationServer);
        this.databaseConnection = databaseConnection;
        initializePacketReceivers();
    }

    private void initializePacketReceivers() {
    }

    public void start(){
        ServerLogger.log("GameServerAuthenticationServer has been started.");
        serverThread.start();
    }
}

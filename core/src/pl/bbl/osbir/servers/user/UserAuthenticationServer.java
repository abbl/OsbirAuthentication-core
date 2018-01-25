package pl.bbl.osbir.servers.user;

import pl.bbl.network.server.Server;
import pl.bbl.network.server.handler.PacketDistributor;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.features.user.authentication.UserAuthenticator;
import pl.bbl.osbir.servers.user.properties.UserAuthenticationServerProperties;
import pl.bbl.osbir.servers.user.user.User;
import pl.bbl.osbir.tools.logger.ServerLogger;

public class UserAuthenticationServer {
    private Server userAuthenticationServer;
    private Thread serverThread;
    private PacketDistributor packetDistributor;
    private DatabaseConnection databaseConnection;

    public UserAuthenticationServer(DatabaseConnection databaseConnection){
        this.packetDistributor = new PacketDistributor();
        this.userAuthenticationServer = new Server(UserAuthenticationServerProperties.PORT, User.class, packetDistributor);
        this.serverThread = new Thread(userAuthenticationServer);
        this.databaseConnection = databaseConnection;
        initializePacketReceivers();
    }

    private void initializePacketReceivers(){
        packetDistributor.registerPacketReceiver(new UserAuthenticator(databaseConnection));
    }

    public void start(){
        ServerLogger.log("UserAuthenticationServer has been started.");
        serverThread.start();
    }
}

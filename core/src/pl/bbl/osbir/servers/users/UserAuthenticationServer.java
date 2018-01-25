package pl.bbl.osbir.servers.users;

import pl.bbl.network.server.Server;
import pl.bbl.network.server.handler.PacketDistributor;
import pl.bbl.osbir.servers.users.properties.UserAuthenticationServerProperties;
import pl.bbl.osbir.servers.users.user.User;
import pl.bbl.osbir.tools.logger.ServerLogger;

public class UserAuthenticationServer {
    private Server userAuthenticationServer;
    private Thread serverThread;
    private PacketDistributor packetDistributor;

    public UserAuthenticationServer(){
        this.packetDistributor = new PacketDistributor();
        this.userAuthenticationServer = new Server(UserAuthenticationServerProperties.PORT, User.class, packetDistributor);
        this.serverThread = new Thread(userAuthenticationServer);
    }

    public void start(){
        ServerLogger.log("Server has been started.");
        serverThread.start();
    }
}

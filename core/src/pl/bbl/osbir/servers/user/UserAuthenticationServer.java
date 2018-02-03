package pl.bbl.osbir.servers.user;

import pl.bbl.network.server.Server;
import pl.bbl.network.server.handler.PacketDistributor;
import pl.bbl.osbir.SegmentCommunicationDirector;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.features.user.authentication.UserAuthenticator;
import pl.bbl.osbir.features.user.information.UserInformationExchanger;
import pl.bbl.osbir.servers.user.properties.UserAuthenticationServerProperties;
import pl.bbl.osbir.servers.user.user.User;
import pl.bbl.osbir.tools.logger.ServerLogger;

import java.util.ArrayList;
import java.util.HashMap;

public class UserAuthenticationServer {
    private Server userAuthenticationServer;
    private Thread serverThread;
    private PacketDistributor packetDistributor;
    private DatabaseConnection databaseConnection;

    private SegmentCommunicationDirector segmentCommunicationDirector;

    public UserAuthenticationServer(DatabaseConnection databaseConnection, SegmentCommunicationDirector segmentCommunicationDirector){
        this.packetDistributor = new PacketDistributor();
        this.userAuthenticationServer = new Server(UserAuthenticationServerProperties.PORT, User.class, packetDistributor);
        this.serverThread = new Thread(userAuthenticationServer);
        this.databaseConnection = databaseConnection;
        initializePacketReceivers();
    }

    private void initializePacketReceivers(){
        packetDistributor.registerPacketReceiver(new UserAuthenticator(databaseConnection));
        packetDistributor.registerPacketReceiver(new UserInformationExchanger(this));
    }

    public void start(){
        ServerLogger.log("UserAuthenticationServer has been started.");
        serverThread.start();
    }

    public ArrayList<HashMap<String,String>> getServerList(){
        return segmentCommunicationDirector.getGameServerList();
    }
}

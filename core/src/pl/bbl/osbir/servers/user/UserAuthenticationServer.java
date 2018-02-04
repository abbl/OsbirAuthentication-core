package pl.bbl.osbir.servers.user;

import pl.bbl.network.server.Server;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handler.PacketDistributor;
import pl.bbl.osbir.SegmentCommunicationDirector;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.features.gameserver.informationexchange.packets.GameServerInformationExchangePackets;
import pl.bbl.osbir.features.user.authentication.UserAuthenticator;
import pl.bbl.osbir.features.user.information.UserInformationExchanger;
import pl.bbl.osbir.servers.gameserver.user.GameServer;
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
        this.segmentCommunicationDirector = segmentCommunicationDirector;
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

    public void verifyUserForGameServer(String userKey, String username, GameServer gameServer) {
        User user = (User) userAuthenticationServer.getUserById(userKey);
        boolean verificationResult = false;

        if(user != null){
            if (user.isAuthenticated() && user.getUsername().equals(username)) {
                verificationResult = true;
            }
        }
        gameServer.sendPacket(GameServerInformationExchangePackets.createUserVerificationResultPacket(userKey, username, verificationResult));
    }

    public ArrayList<HashMap<String,String>> getServerList(){
        return segmentCommunicationDirector.getGameServerList();
    }
}

package pl.bbl.osbir.servers.gameserver;

import pl.bbl.network.server.Server;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handler.PacketDistributor;
import pl.bbl.osbir.SegmentCommunicationDirector;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.features.gameserver.authentication.GameServerAuthenticator;
import pl.bbl.osbir.features.gameserver.informationexchange.GameServerInformationExchanger;
import pl.bbl.osbir.servers.gameserver.properties.GameServerAuthenticationServerProperties;
import pl.bbl.osbir.servers.gameserver.user.GameServer;
import pl.bbl.osbir.servers.user.properties.UserAuthenticationServerProperties;
import pl.bbl.osbir.tools.logger.ServerLogger;

import java.util.ArrayList;
import java.util.HashMap;

public class GameServerAuthenticationServer {
    private SegmentCommunicationDirector segmentCommunicationDirector;
    private Server gameServerAuthenticationServer;
    private Thread serverThread;
    private PacketDistributor packetDistributor;
    private DatabaseConnection databaseConnection;

    public GameServerAuthenticationServer(DatabaseConnection databaseConnection, SegmentCommunicationDirector segmentCommunicationDirector){
        this.packetDistributor = new PacketDistributor();
        this.gameServerAuthenticationServer = new Server(GameServerAuthenticationServerProperties.PORT, GameServer.class, packetDistributor);
        this.serverThread = new Thread(gameServerAuthenticationServer);
        this.databaseConnection = databaseConnection;
        this.segmentCommunicationDirector = segmentCommunicationDirector;
        initializePacketReceivers();
    }

    private void initializePacketReceivers() {
        packetDistributor.registerPacketReceiver(new GameServerAuthenticator(databaseConnection));
        packetDistributor.registerPacketReceiver(new GameServerInformationExchanger(this));
    }

    public void start(){
        ServerLogger.log("GameServerAuthenticationServer has been started.");
        serverThread.start();
    }

    public void verifyUser(String userKey, String username, GameServer gameServer) {
        segmentCommunicationDirector.verifyUser(userKey, username, gameServer);
    }

    public ArrayList<HashMap<String,String>> getGameServerList() {
        ArrayList<HashMap<String,String>> serverList = new ArrayList<>();
        for (AbstractUser abstractUser : gameServerAuthenticationServer.getUsers()){
            if(abstractUser.isAuthenticated()){
                serverList.add(((GameServer)abstractUser).getInformation());
            }
        }
        return serverList;
    }
}

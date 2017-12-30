package pl.bbl.osbir.servers.gameservers.receivers;

import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.features.route.gameserver.authentication.receiver.GameServerAuthenticationReceiver;
import pl.bbl.osbir.features.route.gameserver.information.receiver.InformationExchangeReceiver;
import pl.bbl.osbir.servers.gameservers.gameserver.GameServer;
import pl.bbl.osbir.servers.gameservers.instance.GameServerAuthenticationServer;

public class GameServerReceivers {
    public static final String AUTHENTICATION_RECEIVER = "AUTHENTICATION_PACKETS";
    public static final String INFORMATION_RECEIVER = "INFORMATION_PACKETS";

    public static void addReceivers(GameServer gameServer, DatabaseConnection databaseConnection, GameServerAuthenticationServer gameServerAuthenticationServer){
        PacketHandler packetHandler = gameServer.retrievePacketHandlerFromPipeline();
        packetHandler.addReceiver(new GameServerAuthenticationReceiver(AUTHENTICATION_RECEIVER, gameServer, databaseConnection));
        packetHandler.addReceiver(new InformationExchangeReceiver(INFORMATION_RECEIVER, gameServer, gameServerAuthenticationServer));
    }
}

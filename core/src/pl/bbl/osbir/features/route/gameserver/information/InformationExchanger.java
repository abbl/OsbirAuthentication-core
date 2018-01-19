package pl.bbl.osbir.features.route.gameserver.information;

import pl.bbl.network.packet.Packet;
import pl.bbl.osbir.servers.gameservers.gameserver.GameServer;
import pl.bbl.osbir.tools.logger.ServerLogger;


public class InformationExchanger {
    public static void updateServerInformation(Packet packet, GameServer gameServer){
        gameServer.updateServerInformation((String)packet.getData("name"), (String)packet.getData("host"),
                (int) packet.getData("port"));
        ServerLogger.log("Received server info");
    }
}

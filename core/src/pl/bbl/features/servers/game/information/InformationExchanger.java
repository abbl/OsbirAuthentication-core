package pl.bbl.features.servers.game.information;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.servers.gameservers.gameserver.GameServer;

public class InformationExchanger {
    private static UserHive userHive;

    public InformationExchanger(UserHive userHive){
        InformationExchanger.userHive = userHive;
    }

    public static void updateServerInformation(Packet packet, GameServer gameServer){

    }

    public static void checkIfUserKeyIsCorrect(Packet packet){

    }
}

package pl.bbl.features.route.gameserver.information;

import pl.bbl.features.route.gameserver.information.packets.UserVerificationPacket;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.servers.gameservers.gameserver.GameServer;
import pl.bbl.servers.users.user.User;

public class InformationExchanger {
    private static UserHive userHive;

    public InformationExchanger(UserHive userHive){
        InformationExchanger.userHive = userHive;
    }

    public static void updateServerInformation(Packet packet, GameServer gameServer){
        gameServer.updateServerInformation((String)packet.getData("name"), (String)packet.getData("host"),
                (int) packet.getData("port"));
    }

    public static void checkIfUserKeyIsCorrect(Packet packet, GameServer gameServer){
        for(AbstractUser abstractUser : userHive.getUsers()){
            User user = (User) abstractUser;
            if(user.isAuthenticated())
                if(user.getId().equals(packet.getData("userId"))){
                    gameServer.sendPacket(UserVerificationPacket.createPacket((String)packet.getData("userId"), true));
                    return;
                }
        }
        gameServer.sendPacket(UserVerificationPacket.createPacket((String)packet.getData("userId"), false));
    }
}

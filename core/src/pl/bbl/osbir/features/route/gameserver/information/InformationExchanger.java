package pl.bbl.osbir.features.route.gameserver.information;

import pl.bbl.osbir.features.route.gameserver.information.packets.UserVerificationPacket;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.osbir.servers.gameservers.gameserver.GameServer;
import pl.bbl.osbir.servers.users.user.User;


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
        boolean result = false;
        for(AbstractUser abstractUser : userHive.getUsers()){
            User user = (User) abstractUser;
            if(user.isAuthenticated())
                if(user.getId().equals(packet.getData("userId"))){
                    result = true;
                    break;
                }
        }
        gameServer.sendPacket(UserVerificationPacket.createPacket((String)packet.getData("userId"), result));
    }
}

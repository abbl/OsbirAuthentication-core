package pl.bbl.osbir.features.user.information;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handler.PacketReceiver;
import pl.bbl.osbir.features.user.information.packets.UserInformationExchangerPackets;
import pl.bbl.osbir.servers.user.UserAuthenticationServer;
import pl.bbl.osbir.servers.user.user.User;

public class UserInformationExchanger extends PacketReceiver{
    private static UserAuthenticationServer userAuthenticationServer;

    public UserInformationExchanger(UserAuthenticationServer userAuthenticationServer) {
        super("UserInformationExchanger");
        UserInformationExchanger.userAuthenticationServer = userAuthenticationServer;
    }

    @Override
    public void receivePacket(Packet packet, AbstractUser abstractUser) {
        switch (packet.getPacketType()){
            case "REQUEST_GAMESERVER_LIST":
                sendGameServerList((User) abstractUser);
        }
    }

    private void sendGameServerList(User user) {
        if(user.isAuthenticated()){
            user.sendPacket(UserInformationExchangerPackets.createServerListPacket(userAuthenticationServer.getServerList()));
        }
    }
}

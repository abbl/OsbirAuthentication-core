package pl.bbl.osbir.features.gameserver.informationexchange;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handler.PacketReceiver;
import pl.bbl.osbir.servers.gameserver.GameServerAuthenticationServer;
import pl.bbl.osbir.servers.gameserver.user.GameServer;

public class GameServerInformationExchanger extends PacketReceiver{
    private static GameServerAuthenticationServer gameServerAuthenticationServer;

    public GameServerInformationExchanger(GameServerAuthenticationServer gameServerAuthenticationServer) {
        super("GameServerInformationExchanger");
        GameServerInformationExchanger.gameServerAuthenticationServer = gameServerAuthenticationServer;
    }

    @Override
    public void receivePacket(Packet packet, AbstractUser abstractUser) {
        switch (packet.getPacketType()){
            case "VERIFY_USER":
                verifyUser((String)packet.getData("userKey"), (String)packet.getData("username"), (GameServer) abstractUser);
                break;
            case "UPDATE_GAMESERVER_INFORMATION":
                updateGameServerInformation(packet, (GameServer) abstractUser);
        }
    }

    private void verifyUser(String userKey, String username, GameServer gameServer) {
        gameServerAuthenticationServer.verifyUser(userKey, username, gameServer);
    }

    private void updateGameServerInformation(Packet packet, GameServer gameServer) {
        if(gameServer.isAuthenticated()){
            gameServer.updateInformation((String)packet.getData("name"), (String) packet.getData("host"), (int) packet.getData("port"));
        }
    }
}

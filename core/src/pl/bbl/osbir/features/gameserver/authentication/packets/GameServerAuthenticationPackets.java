package pl.bbl.osbir.features.gameserver.authentication.packets;

import pl.bbl.network.packet.Packet;

public class GameServerAuthenticationPackets {
    public static Packet createGameServerAuthenticationResultPacket(boolean result){
        return new Packet("GAMESERVER_AUTHENTICATION_RESULT").addData("result", result);
    }
}

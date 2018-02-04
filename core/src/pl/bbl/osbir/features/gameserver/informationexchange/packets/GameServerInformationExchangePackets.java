package pl.bbl.osbir.features.gameserver.informationexchange.packets;

import pl.bbl.network.packet.Packet;

public class GameServerInformationExchangePackets {
    public static Packet createUserVerificationResultPacket(String userKey, String username, boolean result){
        return new Packet("USER_VERIFICATION_RESULT").addData("userKey", userKey).addData("username", username).addData("result", result);
    }
}

package pl.bbl.osbir.features.route.gameserver.information.packets;

import pl.bbl.network.packet.Packet;

public class InformationExchangePackets {
    public static Packet createUserVerificationResultPacket(String packetType, String userId, boolean result){
        return new Packet(packetType, "USER_VERIFICATION_RESULT").addData("userId", userId).addData("result", result);
    }
}

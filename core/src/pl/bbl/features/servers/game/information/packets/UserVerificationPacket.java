package pl.bbl.features.servers.game.information.packets;

import pl.bbl.network.packet.Packet;

public class UserVerificationPacket {
    public static Packet createPacket(String userId, boolean result){
        Packet packet = new Packet("INFORMATION_PACKETS", "USER_VERIFICATION_RESULT");
        packet.addData("userId", userId);
        packet.addData("result", result);
        return packet;
    }
}

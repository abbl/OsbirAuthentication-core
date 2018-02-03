package pl.bbl.osbir.features.user.authentication.packets;

import pl.bbl.network.packet.Packet;

public class UserAuthenticationPackets {
    public static Packet createUserAuthenticationResultPacket(boolean result){
        return new Packet("USER_AUTHENTICATION_RESULT").addData("result", result);
    }

    public static Packet createUserIdPacket(String userId){
        return new Packet("USER_ID").addData("userId", userId);
    }
}

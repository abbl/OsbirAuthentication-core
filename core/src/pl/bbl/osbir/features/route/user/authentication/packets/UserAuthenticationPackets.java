package pl.bbl.osbir.features.route.user.authentication.packets;


import pl.bbl.network.packet.Packet;
import pl.bbl.osbir.servers.users.receivers.UserAuthenticationReceivers;

public class UserAuthenticationPackets {
    public static Packet createAuthenticationResultPacket(boolean result){
        return new Packet(UserAuthenticationReceivers.USER_AUTHENTICATION, "AUTHENTICATION_RESULT").addData("result", result);
    }

    public static Packet createUserIdPacket(String userId){
        return new Packet(UserAuthenticationReceivers.USER_AUTHENTICATION, "USER_ID").addData("userId", userId);
    }
}

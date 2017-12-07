package pl.bbl.features.authentication.user.packets;


import pl.bbl.network.packet.Packet;

public class UserAuthenticationResult {
    public static Packet createPacket(boolean result){
        return preparePacket(result);
    }

    private static Packet preparePacket(boolean result){
        Packet basicPacket = new Packet("LOGIN_RESULT");
        basicPacket.addData("result", result);
        return basicPacket;
    }
}

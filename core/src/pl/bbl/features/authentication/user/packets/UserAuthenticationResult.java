package pl.bbl.features.authentication.user.packets;

import pl.bbl.network.packet.BasicPacket;

public class UserAuthenticationResult {
    public static BasicPacket createPacket(boolean result){
        return preparePacket(result);
    }

    private static BasicPacket preparePacket(boolean result){
        BasicPacket basicPacket = new BasicPacket("LOGIN_RESULT");
        basicPacket.addData("result", result);
        return basicPacket;
    }
}

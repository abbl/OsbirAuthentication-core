package pl.bbl.osbir.features.user.information.packets;

import pl.bbl.network.packet.Packet;

import java.util.ArrayList;
import java.util.HashMap;

public class UserInformationExchangerPackets {
    public static Packet createServerListPacket(ArrayList<HashMap<String,String>> gameserverList){
        return new Packet("GAMESERVER_LIST").addData("list", gameserverList);
    }
}

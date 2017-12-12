package pl.bbl.features.servers.game.information.receiver;

import pl.bbl.features.servers.game.information.InformationExchanger;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.servers.gameservers.gameserver.GameServer;

public class InformationExchangeReceiver extends PacketReceiver{
    private GameServer gameServer;

    public InformationExchangeReceiver(String packetType, GameServer gameServer, UserHive userHive){
        super(packetType);
        this.gameServer = gameServer;
    }

    @Override
    public boolean receive(Packet packet) {
        switch (packet.packetPurpose){
            case "UPDATE_SERVER_INFORMATION":
                InformationExchanger.updateServerInformation(packet, gameServer);
                return true;
            case "VERIFY_USER":
                InformationExchanger.checkIfUserKeyIsCorrect(packet);
        }
        return false;
    }
}

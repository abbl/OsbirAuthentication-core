package pl.bbl.osbir.features.route.gameserver.information.receiver;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.osbir.features.route.gameserver.information.InformationExchanger;
import pl.bbl.osbir.servers.gameservers.gameserver.GameServer;


import java.util.logging.Level;
import java.util.logging.Logger;

public class InformationExchangeReceiver extends PacketReceiver{
    private GameServer gameServer;

    public InformationExchangeReceiver(String packetType, GameServer gameServer, UserHive userHive){
        super(packetType);
        this.gameServer = gameServer;
    }

    @Override
    public boolean receive(Packet packet) {
        if(gameServer.isAuthenticated())
            switch (packet.packetPurpose){
                case "UPDATE_SERVER_INFORMATION":
                    InformationExchanger.updateServerInformation(packet, gameServer);
                    return true;
                case "VERIFY_USER":
                    InformationExchanger.checkIfUserKeyIsCorrect(packet, gameServer);
                    return true;
            }
        else
            Logger.getLogger(InformationExchangeReceiver.class.getName()).log(Level.WARNING, "Unauthorized server trying to access InformationExchanger.");
        return false;
    }
}

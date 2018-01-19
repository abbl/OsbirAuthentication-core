package pl.bbl.osbir.features.route.gameserver.information.receiver;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.osbir.features.route.gameserver.information.InformationExchanger;
import pl.bbl.osbir.features.route.gameserver.information.packets.InformationExchangePackets;
import pl.bbl.osbir.servers.gameservers.gameserver.GameServer;
import pl.bbl.osbir.servers.gameservers.instance.GameServerAuthenticationServer;
import pl.bbl.osbir.tools.logger.ServerLogger;

public class InformationExchangeReceiver extends PacketReceiver{
    private GameServerAuthenticationServer gameServerAuthenticationServer;

    public InformationExchangeReceiver(String packetType, GameServer gameServer, GameServerAuthenticationServer gameServerAuthenticationServer){
        super(packetType, gameServer);
        this.gameServerAuthenticationServer = gameServerAuthenticationServer;
    }

    @Override
    public boolean receive(Packet packet) {
        if(getGameServer().isAuthenticated())
            switch (packet.packetPurpose){
                case "UPDATE_SERVER_INFORMATION":
                    InformationExchanger.updateServerInformation(packet, getGameServer());
                    return true;
                case "VERIFY_USER":
                    requestUserVerification((String)packet.getData("userId"));
                    return true;
            }
        else
            ServerLogger.log("Unauthorized GameServer attempted to access InformationExchangeReceiver.");
        return false;
    }

    private void requestUserVerification(String userId){
        gameServerAuthenticationServer.requestUserIdVerification(getGameServer().getId(), userId);
    }

    public void sendUserVerificationResult(String userId, boolean result){
        sendPacket(InformationExchangePackets.createUserVerificationResultPacket(receiverType, userId, result));
    }

    private GameServer getGameServer(){
        return (GameServer) abstractUser;
    }
}

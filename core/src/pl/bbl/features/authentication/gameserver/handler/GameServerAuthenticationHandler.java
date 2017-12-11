package pl.bbl.features.authentication.gameserver.handler;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;

public class GameServerAuthenticationHandler extends PacketReceiver {

    protected GameServerAuthenticationHandler(String receiverType) {
        super(receiverType);
    }

    @Override
    public boolean receive(Packet packet) {
        return false;
    }
}

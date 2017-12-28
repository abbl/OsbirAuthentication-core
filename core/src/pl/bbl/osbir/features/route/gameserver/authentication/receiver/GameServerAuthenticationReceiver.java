package pl.bbl.osbir.features.route.gameserver.authentication.receiver;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.features.route.gameserver.authentication.GameServerAuthenticator;
import pl.bbl.osbir.servers.gameservers.gameserver.GameServer;

public class GameServerAuthenticationReceiver extends PacketReceiver {
    private GameServer gameServer;

    public GameServerAuthenticationReceiver(String receiverType, GameServer gameServer, DatabaseConnection databaseConnection) {
        super(receiverType);
        new GameServerAuthenticator(databaseConnection);
        this.gameServer = gameServer;
    }

    @Override
    public boolean receive(Packet packet) {
        switch (packet.packetPurpose){
            case "AUTHENTICATION_START":
                GameServerAuthenticator.authenticateGameServer(gameServer, packet);
                return true;
        }
        return false;
    }
}

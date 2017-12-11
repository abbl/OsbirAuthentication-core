package pl.bbl.features.authentication.gameserver.handler;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.AbstractPacketHandler;

public class GameServerAuthenticationHandler extends AbstractPacketHandler {

    public GameServerAuthenticationHandler(DatabaseConnection databaseConnection){

    }

    @Override
    protected void handlePacket(Packet packet) {
        switch (packet.packetType){
            case "START_AUTHENTICATION":

        }
    }
}

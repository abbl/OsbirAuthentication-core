package pl.bbl.osbir.features.gameserver.authentication;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handler.PacketReceiver;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.database.statements.gameservers.GameServerTableStatements;
import pl.bbl.osbir.features.gameserver.authentication.packets.GameServerAuthenticationPackets;
import pl.bbl.osbir.servers.gameserver.user.GameServer;

import java.sql.SQLException;

public class GameServerAuthenticator extends PacketReceiver{
    private static DatabaseConnection databaseConnection;

    public GameServerAuthenticator(DatabaseConnection databaseConnection) {
        super("GameServerAuthenticator");
        GameServerAuthenticator.databaseConnection = databaseConnection;
    }

    @Override
    public void receivePacket(Packet packet, AbstractUser abstractUser) {
        switch (packet.getPacketType()){
            case "START_GAMESERVER_AUTHENTICATION":
                authenticateGameServer((String)packet.getData("authenticationKey"), (GameServer) abstractUser);
        }
    }

    private void authenticateGameServer(String authenticationKey, GameServer gameServer) {
        boolean authenticationResult = false;
        try {
            if(GameServerTableStatements.getGameServerData(databaseConnection, authenticationKey).next())
                authenticationResult = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        gameServer.setAuthenticated(authenticationResult);
        gameServer.sendPacket(GameServerAuthenticationPackets.createGameServerAuthenticationResultPacket(authenticationResult));
    }
}

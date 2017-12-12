package pl.bbl.features.authentication.gameserver;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.database.statements.gameservers.GameServerTableStatements;
import pl.bbl.tools.misc.ObjectComparison;
import pl.bbl.features.authentication.user.packets.AuthenticationResultPacket;
import pl.bbl.network.packet.Packet;
import pl.bbl.servers.gameservers.gameserver.GameServer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameServerAuthenticator {
    private static DatabaseConnection databaseConnection;

    public GameServerAuthenticator(DatabaseConnection databaseConnection){
        if(ObjectComparison.doesObjectQualifyForChange(GameServerAuthenticator.databaseConnection, databaseConnection))
            GameServerAuthenticator.databaseConnection = databaseConnection;
    }

    public static void authenticateGameServer(GameServer gameServer, Packet packet){
        String gameServerAuthenticationKey = (String) packet.getData("authenticationKey");

        if(GameServerTableStatements.getGameServerData(databaseConnection, gameServerAuthenticationKey) != null){
            gameServer.setAuthenticationKey(gameServerAuthenticationKey);
            gameServer.setAuthenticated(true);
            Logger.getLogger(GameServerAuthenticator.class.getName()).log(Level.INFO, "GameServer has been authenticated.");
        }
        gameServer.sendPacket(AuthenticationResultPacket.createPacket(gameServer.isAuthenticated()));
    }
}

package pl.bbl.osbir.features.route.gameserver.authentication;

import pl.bbl.network.packet.Packet;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.database.statements.gameservers.GameServerTableStatements;
import pl.bbl.osbir.features.route.user.authentication.packets.UserAuthenticationPackets;
import pl.bbl.osbir.servers.gameservers.gameserver.GameServer;
import pl.bbl.osbir.tools.logger.ServerLogger;
import pl.bbl.osbir.tools.misc.ObjectComparison;

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
            ServerLogger.log("GameServer has been authenticated.");
        }
        gameServer.sendPacket(UserAuthenticationPackets.createAuthenticationResultPacket(gameServer.isAuthenticated()));
    }
}

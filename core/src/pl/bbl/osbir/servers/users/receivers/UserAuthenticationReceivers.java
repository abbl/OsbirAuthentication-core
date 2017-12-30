package pl.bbl.osbir.servers.users.receivers;

import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.features.route.user.authentication.receiver.UserAuthenticationReceiver;
import pl.bbl.osbir.servers.users.user.User;

public class UserAuthenticationReceivers {
    public static final String USER_AUTHENTICATION = "AUTHENTICATION_PACKETS";

    public static void addReceivers(User user, DatabaseConnection databaseConnection){
        PacketHandler packetHandler = user.retrievePacketHandlerFromPipeline();
        packetHandler.addReceiver(new UserAuthenticationReceiver(USER_AUTHENTICATION, user, databaseConnection));
    }
}

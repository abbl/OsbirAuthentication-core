package pl.bbl.osbir.features.route.user.authentication.receiver;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.features.route.user.authentication.UserAuthenticator;
import pl.bbl.osbir.features.route.user.authentication.packets.UserAuthenticationPackets;
import pl.bbl.osbir.servers.users.user.User;
import pl.bbl.osbir.tools.logger.ServerLogger;

public class UserAuthenticationReceiver extends PacketReceiver {
    private User user;

    public UserAuthenticationReceiver(String packetType, User user, DatabaseConnection databaseConnection) {
        super(packetType);
        this.user = user;
        new UserAuthenticator(databaseConnection);
    }

    @Override
    public boolean receive(Packet packet) {
        switch (packet.packetPurpose){
            case "AUTHENTICATION_START":
                UserAuthenticator.startLoginProcess(packet, user);
                return true;
            case "REQUEST_USER_ID":
                sendUserId();
                return true;
        }
        return false;
    }

    private void sendUserId(){
        if(user.isAuthenticated())
            user.sendPacket(UserAuthenticationPackets.createUserIdPacket(user.getId()));
        else
            ServerLogger.log("User without verification tried to access his userId");
    }
}

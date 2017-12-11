package pl.bbl.features.authentication.user.receiver;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.features.authentication.user.UserAuthenticator;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.PacketReceiver;
import pl.bbl.servers.users.user.User;

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
        }
        return false;
    }

    public User getUser(){
        return user;
    }
}

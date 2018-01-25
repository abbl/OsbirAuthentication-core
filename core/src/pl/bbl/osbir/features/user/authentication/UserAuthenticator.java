package pl.bbl.osbir.features.user.authentication;

import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handler.PacketReceiver;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.database.statements.users.UsersTableStatements;
import pl.bbl.osbir.features.user.authentication.packets.UserAuthenticationPackets;
import pl.bbl.osbir.servers.users.user.User;
import pl.bbl.osbir.tools.logger.ServerLogger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthenticator extends PacketReceiver{
    private DatabaseConnection databaseConnection;

    public UserAuthenticator(DatabaseConnection databaseConnection) {
        super("UserAuthenticator");
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void receivePacket(Packet packet, AbstractUser abstractUser) {
        switch (packet.getPacketType()){
            case "REQUEST_AUTHENTICATION_START":
                authenticateUser(packet, (User) abstractUser);
                break;
            case "REQUEST_USER_ID":
                sendUserId((User) abstractUser);
                break;
        }
    }

    private void authenticateUser(Packet packet, User user){
        String username = (String) packet.getData("username");
        String password = (String) packet.getData("password");
        ResultSet resultSet = UsersTableStatements.getUserData(databaseConnection, username);

        if(resultSet != null){
            try {
                if(resultSet.getString("password").equals(password)){
                    ServerLogger.log( username + " has been successfully logged in");
                    user.setAuthenticated(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (!user.isAuthenticated()){
            ServerLogger.log( username + " tried to log in with wrong username or password.");
        }
        user.sendPacket(UserAuthenticationPackets.createUserAuthenticationResultPacket(user.isAuthenticated()));
    }

    private void sendUserId(User user){
        if(user.isAuthenticated()){
            user.sendPacket(UserAuthenticationPackets.createUserIdPacket(user.getId()));
        }else{
            ServerLogger.log("Unauthenticated user tried to access his userId.");
        }
    }
}

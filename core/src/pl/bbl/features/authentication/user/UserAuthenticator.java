package pl.bbl.features.authentication.user;

import pl.bbl.database.DatabaseConnection;
import pl.bbl.network.packet.BasicPacket;
import pl.bbl.server.user.User;

public class UserAuthenticator {
    private static DatabaseConnection databaseConnection;

    public UserAuthenticator(DatabaseConnection databaseConnection){
        UserAuthenticator.databaseConnection = databaseConnection;
    }

    public static void startLoginProcess(BasicPacket basicPacket, User user){
        String login = (String) basicPacket.getData("login");
        String password = (String) basicPacket.getData("password");
    }
}

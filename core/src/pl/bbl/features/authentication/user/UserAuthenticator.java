package pl.bbl.features.authentication.user;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.database.statements.users.UsersTableStatements;
import pl.bbl.features.authentication.user.packets.UserAuthenticationResult;
import pl.bbl.network.packet.Packet;
import pl.bbl.servers.users.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthenticator {
    private static DatabaseConnection databaseConnection;

    public UserAuthenticator(DatabaseConnection databaseConnection){
        if(UserAuthenticator.databaseConnection == null ||
                !UserAuthenticator.databaseConnection.equals(databaseConnection))
            UserAuthenticator.databaseConnection = databaseConnection;
    }

    public static void startLoginProcess(Packet packet, User user){
        String login = (String) packet.getData("login");
        String password = (String) packet.getData("password");

        if(searchDatabaseForAccount(login, password)){
            user.setAuthenticated(true);
        }
        user.sendPacket(UserAuthenticationResult.createPacket(user.isAuthenticated()));
    }

    private static boolean searchDatabaseForAccount(String login, String password){
        ResultSet resultSet = UsersTableStatements.getUserData(databaseConnection, login);
        if(resultSet != null){
            try {
                if(resultSet.getString("password").equals(password)){
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

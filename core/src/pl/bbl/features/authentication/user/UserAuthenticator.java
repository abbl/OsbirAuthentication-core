package pl.bbl.features.authentication.user;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.database.statements.users.UsersTableStatements;
import pl.bbl.features.authentication.user.packets.UserAuthenticationResult;
import pl.bbl.network.packet.BasicPacket;
import pl.bbl.servers.users.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthenticator {
    private static DatabaseConnection databaseConnection;

    public UserAuthenticator(DatabaseConnection databaseConnection){
        if(UserAuthenticator.databaseConnection == null)
            UserAuthenticator.databaseConnection = databaseConnection;
    }

    public static void startLoginProcess(BasicPacket basicPacket, User user){
        String login = (String) basicPacket.getData("login");
        String password = (String) basicPacket.getData("password");

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

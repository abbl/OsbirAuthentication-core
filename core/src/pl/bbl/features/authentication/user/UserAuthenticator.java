package pl.bbl.features.authentication.user;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.database.statements.users.UsersTableStatements;
import pl.bbl.network.packet.BasicPacket;
import pl.bbl.server.user.User;

import java.sql.ResultSet;

public class UserAuthenticator {
    private static DatabaseConnection databaseConnection;

    public UserAuthenticator(DatabaseConnection databaseConnection){
        if(UserAuthenticator.databaseConnection == null)
            UserAuthenticator.databaseConnection = databaseConnection;
    }

    public static void startLoginProcess(BasicPacket basicPacket, User user){
        String login = (String) basicPacket.getData("login");
        String password = (String) basicPacket.getData("password");
    }

    private static void searchDatabaseForAccount(String login, String password){
        ResultSet resultSet = UsersTableStatements.getUserData(databaseConnection, login);
        if(resultSet != null){
            if()
        }
    }
}

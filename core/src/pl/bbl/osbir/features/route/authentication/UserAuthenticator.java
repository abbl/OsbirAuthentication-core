package pl.bbl.osbir.features.route.authentication;

import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.database.statements.users.UsersTableStatements;
import pl.bbl.osbir.features.route.authentication.packets.UserAuthenticationPackets;
import pl.bbl.network.packet.Packet;
import pl.bbl.osbir.servers.users.user.User;
import pl.bbl.osbir.tools.misc.ObjectComparison;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserAuthenticator {
    private static DatabaseConnection databaseConnection;

    public UserAuthenticator(DatabaseConnection databaseConnection){
        if(ObjectComparison.doesObjectQualifyForChange(UserAuthenticator.databaseConnection, databaseConnection))
            UserAuthenticator.databaseConnection = databaseConnection;
    }

    public static void startLoginProcess(Packet packet, User user){
        String login = (String) packet.getData("login");
        String password = (String) packet.getData("password");

        if(searchDatabaseForAccount(login, password)){
            user.setAuthenticated(true);
            Logger.getLogger(UserAuthenticator.class.getName()).log(Level.INFO, "[UserAuthenticationServer]" + login + " has been authenticated.");
        }
        user.sendPacket(UserAuthenticationPackets.createPacket(user.isAuthenticated()));
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

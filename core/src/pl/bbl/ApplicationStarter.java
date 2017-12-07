package pl.bbl;

import pl.bbl.database.Database;
import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.servers.gameservers.GameServerAuthenticationServer;
import pl.bbl.servers.gameservers.properties.GameServerProperties;
import pl.bbl.servers.users.UserAuthenticationServer;
import pl.bbl.servers.users.properties.UserServerProperties;
import pl.bbl.servers.users.user.User;

public class ApplicationStarter {
    public static void main(String args[]){
        try {
            DatabaseConnection databaseConnection = Database.establishDatabaseConnection();
            if(!databaseConnection.getConnection().isClosed()){
                new Thread(new UserAuthenticationServer(UserServerProperties.USER_CONNECTION_PORT, new User(), databaseConnection)).start();
                System.out.println("[UserAuthenticationServer]Server started.");
                new Thread(new GameServerAuthenticationServer(GameServerProperties.GAMESERVER_CONNECTION_PORT, new User(), databaseConnection)).start();
                System.out.println("[GameServerAuthenticationServer]Server started.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

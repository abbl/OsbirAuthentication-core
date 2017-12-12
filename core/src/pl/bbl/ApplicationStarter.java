package pl.bbl;

import pl.bbl.database.Database;
import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.network.packet.Packet;
import pl.bbl.servers.gameservers.GameServerAuthenticationServer;
import pl.bbl.servers.gameservers.gameserver.GameServer;
import pl.bbl.servers.gameservers.properties.GameServerProperties;
import pl.bbl.servers.users.UserAuthenticationServer;
import pl.bbl.servers.users.properties.UserServerProperties;
import pl.bbl.servers.users.user.User;
import pl.bbl.tools.test.client.TestClient;

public class ApplicationStarter {
    public static void main(String args[]){
        try {
            DatabaseConnection databaseConnection = Database.establishDatabaseConnection();
            if(!databaseConnection.getConnection().isClosed()){
                new Thread(new UserAuthenticationServer(UserServerProperties.USER_CONNECTION_PORT, User.class, databaseConnection)).start();
                System.out.println("[UserAuthenticationServer]Server started.");
                new Thread(new GameServerAuthenticationServer(GameServerProperties.GAMESERVER_CONNECTION_PORT, GameServer.class, databaseConnection)).start();
                System.out.println("[GameServerAuthenticationServer]Server started.");
                TestClient testClient = new TestClient("localhost",  9987);
                new Thread(testClient).start();
                Packet login = new Packet("AUTHENTICATION_PACKETS", "AUTHENTICATION_START");
                login.addData("login", "Abbl");
                login.addData("password", "123");
                testClient.write(login);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

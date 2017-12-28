package pl.bbl;

import pl.bbl.osbir.database.Database;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.network.packet.Packet;
import pl.bbl.servers.gameservers.instance.GameServerAuthenticationServer;
import pl.bbl.servers.gameservers.gameserver.GameServer;
import pl.bbl.servers.gameservers.properties.GameServerProperties;
import pl.bbl.servers.users.instance.UserAuthenticationServer;
import pl.bbl.servers.users.properties.UserServerProperties;
import pl.bbl.servers.users.user.User;
import pl.bbl.tools.test.client.TestClient;

public class ApplicationStarter {
    private static LocalServersDataExchanger localServersDataExchanger;

    public static void main(String args[]){
        try {
            DatabaseConnection databaseConnection = Database.establishDatabaseConnection();
            if(!databaseConnection.getConnection().isClosed()){
                UserAuthenticationServer userAuthenticationServer = new UserAuthenticationServer(UserServerProperties.USER_CONNECTION_PORT, User.class, databaseConnection);
                GameServerAuthenticationServer gameServerAuthenticationServer = new GameServerAuthenticationServer(GameServerProperties.GAMESERVER_CONNECTION_PORT, GameServer.class, databaseConnection);
                localServersDataExchanger = new LocalServersDataExchanger(userAuthenticationServer, gameServerAuthenticationServer);
                userAuthenticationServer.setLocalServersDataExchanger(localServersDataExchanger);
                gameServerAuthenticationServer.setLocalServersDataExchanger(localServersDataExchanger);
                new Thread(userAuthenticationServer).start();
                new Thread(gameServerAuthenticationServer).start();
                System.out.println("[UserAuthenticationServer]Server started.");
                System.out.println("[GameServerAuthenticationServer]Server started.");
                testClientConnection();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testClientConnection(){
        TestClient testClient = new TestClient("localhost",  9987);
        new Thread(testClient).start();
        Packet login = new Packet("AUTHENTICATION_PACKETS", "AUTHENTICATION_START");
        login.addData("login", "abbl");
        login.addData("password", "123");
        testClient.write(login);
    }
}

package pl.bbl;

import pl.bbl.servers.users.UserAuthenticationServer;
import pl.bbl.servers.users.properties.ServerProperties;
import pl.bbl.servers.users.user.User;

public class ApplicationStarter {
    public static void main(String args[]){
        try {
            new UserAuthenticationServer(ServerProperties.USER_CONNECTION_PORT, new User()).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

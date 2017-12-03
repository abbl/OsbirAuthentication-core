package pl.bbl;

import pl.bbl.server.AuthenticationServer;
import pl.bbl.server.properties.ServerProperties;
import pl.bbl.server.user.User;

public class ApplicationStarter {
    public static void main(String args[]){
        try {
            new AuthenticationServer(ServerProperties.USER_CONNECTION_PORT, new User()).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

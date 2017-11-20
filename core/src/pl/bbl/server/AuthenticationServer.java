package pl.bbl.server;

import pl.bbl.server.properties.ServerProperties;
import pl.bbl.server.starter.ServerStarter;

public class AuthenticationServer {
    public static void main(String args[]) throws Exception{
        new ServerStarter(ServerProperties.USER_CONNECTION_PORT).run();
    }
}

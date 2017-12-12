package pl.bbl.servers;

import pl.bbl.network.server.hive.UserHive;
import pl.bbl.servers.gameservers.GameServerAuthenticationServer;
import pl.bbl.servers.users.UserAuthenticationServer;

public class LocalServersDataExchanger {
    private UserAuthenticationServer userAuthenticationServer;
    private GameServerAuthenticationServer gameServerAuthenticationServer;

    public LocalServersDataExchanger(UserAuthenticationServer userAuthenticationServer, GameServerAuthenticationServer gameServerAuthenticationServer){
        this.userAuthenticationServer = userAuthenticationServer;
        this.gameServerAuthenticationServer = gameServerAuthenticationServer;
    }

    public UserHive getUserHive(ServerTypes serverType){
        switch (serverType){
            case USER_AUTHENTICATION_SERVER:
                return userAuthenticationServer.getUserHive();
            case GAMESERVER_AUTHENTICATION_SERVER:
                return gameServerAuthenticationServer.getUserHive();
        }
        return null;
    }
}

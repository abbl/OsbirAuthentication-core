package pl.bbl.servers.gameservers.gameserver;

import pl.bbl.network.server.connection.AbstractUser;

public class GameServer extends AbstractUser{
    private String authenticationKey;

    public GameServer(){

    }

    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }
}

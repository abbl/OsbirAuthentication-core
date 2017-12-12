package pl.bbl.servers.gameservers.gameserver;

import io.netty.channel.Channel;
import pl.bbl.network.server.connection.AbstractUser;

public class GameServer extends AbstractUser{
    private String authenticationKey;
    private boolean readyToAcceptPlayers;
    private String name;
    private String host;
    private int port;

    public GameServer(String id, Channel channel) {
        super(id, channel);
    }

    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    public void updateServerInformation(String name, String host, int port){
        if(isAuthenticated()){
            this.name = name;
            this.host = host;
            this.port = port;
        }
    }

    public void setReady(boolean readyToAcceptPlayers){
        this.readyToAcceptPlayers = readyToAcceptPlayers;
    }

    public boolean isReady(){
        return readyToAcceptPlayers;
    }
}

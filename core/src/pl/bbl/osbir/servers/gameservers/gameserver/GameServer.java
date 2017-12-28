package pl.bbl.osbir.servers.gameservers.gameserver;

import io.netty.channel.Channel;
import pl.bbl.network.server.connection.AbstractUser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameServer extends AbstractUser{
    private String authenticationKey;
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
        Logger.getLogger(GameServer.class.getName()).log(Level.INFO, "GameServer information updated." + name + " " + host + " " + port);
        this.name = name;
        this.host = host;
        this.port = port;
    }
}

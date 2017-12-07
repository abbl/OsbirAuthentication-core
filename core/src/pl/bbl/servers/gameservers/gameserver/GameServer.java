package pl.bbl.servers.gameservers.gameserver;

import io.netty.channel.Channel;
import pl.bbl.network.server.connection.AbstractUser;

public class GameServer extends AbstractUser{
    private String authenticationKey;

    public GameServer(String id, Channel channel) {
        super(id, channel);
    }

    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }
}

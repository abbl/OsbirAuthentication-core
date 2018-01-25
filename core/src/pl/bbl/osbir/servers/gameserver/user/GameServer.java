package pl.bbl.osbir.servers.gameserver.user;

import io.netty.channel.Channel;
import pl.bbl.network.server.connection.AbstractUser;

public class GameServer extends AbstractUser{
    public GameServer(String id, Channel channel) {
        super(id, channel);
    }
}

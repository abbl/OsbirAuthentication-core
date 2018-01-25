package pl.bbl.osbir.servers.user.user;

import io.netty.channel.Channel;
import pl.bbl.network.server.connection.AbstractUser;

public class User extends AbstractUser{
    public User(String id, Channel channel) {
        super(id, channel);
    }
}

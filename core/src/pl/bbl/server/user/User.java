package pl.bbl.server.user;

import io.netty.channel.ChannelHandlerContext;
import pl.bbl.network.server.connection.AbstractUser;

public class User extends AbstractUser{
    public User(){}

    public User(ChannelHandlerContext channelHandlerContext){
        super(channelHandlerContext);
    }
}

package pl.bbl.osbir.servers.user.user;

import io.netty.channel.Channel;
import pl.bbl.network.server.connection.AbstractUser;

public class User extends AbstractUser{
    private String username;
    private String password;

    public User(String id, Channel channel) {
        super(id, channel);
    }

    public void setCredentials(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}

package pl.bbl.server;

import io.netty.channel.ChannelPipeline;
import pl.bbl.network.server.BasicServer;
import pl.bbl.network.server.connection.AbstractUser;

public class AuthenticationServer extends BasicServer {

    public AuthenticationServer(int port, AbstractUser abstractUser) {
        super(port, abstractUser);
    }

    @Override
    protected void addHandlersToChannel(ChannelPipeline pipeline) {

    }
}

package pl.bbl.features.authentication.user.handler;

import io.netty.channel.ChannelHandlerContext;
import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.features.authentication.user.UserAuthenticator;
import pl.bbl.network.packet.Packet;
import pl.bbl.network.server.handlers.AbstractPacketHandler;
import pl.bbl.servers.users.user.User;

public class UserAuthenticationHandler extends AbstractPacketHandler {
    private User user;

    public UserAuthenticationHandler(User user, DatabaseConnection databaseConnection) {
        this.user = user;
        new UserAuthenticator(databaseConnection);
    }

    @Override
    protected void handlePacket(Packet packet) {
        switch (packet.packetType){
            case "START_LOGIN":
                UserAuthenticator.startLoginProcess(packet, user);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    public User getUser(){
        return user;
    }
}

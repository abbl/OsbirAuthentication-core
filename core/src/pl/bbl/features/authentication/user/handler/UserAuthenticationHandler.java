package pl.bbl.features.authentication.user.handler;

import io.netty.channel.ChannelHandlerContext;
import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.features.authentication.user.UserAuthenticator;
import pl.bbl.network.packet.BasicPacket;
import pl.bbl.network.server.handlers.UserHandler;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.servers.users.user.User;


public class UserAuthenticationHandler extends UserHandler{
    private UserAuthenticator userAuthenticator;
    private User user;

    public UserAuthenticationHandler(UserHive userHive, DatabaseConnection databaseConnection) {
        super(userHive);
        userAuthenticator = new UserAuthenticator(databaseConnection);
    }

    @Override
    protected void handlePacket(BasicPacket basicPacket) {
        switch (basicPacket.packetType){
            case "START_LOGIN":
                UserAuthenticator.startLoginProcess(basicPacket, user);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        user = (User) userHive.getUserByContextHandler(ctx);
    }

    public User getUser(){
        return user;
    }
}

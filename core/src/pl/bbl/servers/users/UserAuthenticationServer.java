package pl.bbl.servers.users;

import io.netty.channel.ChannelPipeline;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.features.authentication.user.handler.UserAuthenticationHandler;
import pl.bbl.network.server.BasicServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.servers.users.user.User;

public class UserAuthenticationServer extends BasicServer {
    private DatabaseConnection databaseConnection;

    public UserAuthenticationServer(int port, AbstractUser abstractUser, DatabaseConnection databaseConnection) {
        super(port, abstractUser);
        this.databaseConnection = databaseConnection;
    }

    /**
     * WORK THIS ONE OUT
     * XAXAXAXAXAXAX
     * DONT FORGET IT WILL FAIL WITH COMPILATION ANYWAY
     * HELLO FUTURE ME.
     */
    @Override
    protected void addHandlersToChannel(ChannelPipeline pipeline) {
        UserAuthenticationHandler userAuthenticationServer = new UserAuthenticationHandler(userHive, databaseConnection);
        pipeline.addLast(userAuthenticationServer);
        User user = userAuthenticationServer.getUser();
        if(user != null){
            System.out.println("Bangla");
        }
    }


}

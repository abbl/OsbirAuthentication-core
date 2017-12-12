package pl.bbl.servers.users;

import io.netty.channel.ChannelPipeline;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.features.authentication.user.receiver.UserAuthenticationReceiver;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.servers.LocalServersDataExchanger;
import pl.bbl.servers.users.user.User;

public class UserAuthenticationServer extends AbstractServer {
    private DatabaseConnection databaseConnection;
    private LocalServersDataExchanger localServersDataExchanger;

    public UserAuthenticationServer(int port, Class className, DatabaseConnection databaseConnection) {
        super(port, className);
        this.databaseConnection = databaseConnection;
    }

    @Override
    protected AbstractUser addHandlersToChannel(ChannelPipeline pipeline) {
        User user = (User) super.addHandlersToChannel(pipeline);
        PacketHandler packetHandler = new PacketHandler();

        pipeline.addLast(packetHandler);
        addPacketReceivers(packetHandler, user);

        return user;
    }

    private void addPacketReceivers(PacketHandler packetHandler, User user){
        packetHandler.addReceiver(new UserAuthenticationReceiver("AUTHENTICATION_PACKETS", user, databaseConnection));
    }

    public UserHive getUserHive(){
        return userHive;
    }

    public void setLocalServersDataExchanger(LocalServersDataExchanger localServersDataExchanger) {
        this.localServersDataExchanger = localServersDataExchanger;
    }
}

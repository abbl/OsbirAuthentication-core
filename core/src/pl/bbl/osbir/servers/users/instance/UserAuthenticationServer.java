package pl.bbl.osbir.servers.users.instance;

import io.netty.channel.ChannelPipeline;

import pl.bbl.osbir.SegmentCommunicationDirector;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.features.route.authentication.receiver.UserAuthenticationReceiver;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.osbir.servers.users.user.User;


public class UserAuthenticationServer extends AbstractServer {
    private DatabaseConnection databaseConnection;
    private SegmentCommunicationDirector segmentCommunicationDirector;

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

    }

    public UserHive getUserHive(){
        return userHive;
    }

    public void setLocalServersDataExchanger(SegmentCommunicationDirector segmentCommunicationDirector) {
        this.segmentCommunicationDirector = segmentCommunicationDirector;
    }
}

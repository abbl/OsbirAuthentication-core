package pl.bbl.osbir.servers.gameservers.instance;

import io.netty.channel.ChannelPipeline;
import pl.bbl.osbir.SegmentCommunicationDirector;
import pl.bbl.osbir.database.connection.DatabaseConnection;
import pl.bbl.osbir.features.route.gameserver.authentication.receiver.GameServerAuthenticationReceiver;
import pl.bbl.network.server.AbstractServer;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.network.server.handlers.PacketHandler;
import pl.bbl.network.server.hive.UserHive;
import pl.bbl.osbir.features.route.gameserver.information.receiver.InformationExchangeReceiver;
import pl.bbl.osbir.servers.gameservers.GameServerAuthenticationWrapper;
import pl.bbl.osbir.servers.gameservers.gameserver.GameServer;
import pl.bbl.osbir.servers.gameservers.receivers.GameServerReceivers;
import pl.bbl.osbir.tools.logger.ServerLogger;

import java.util.ArrayList;
import java.util.List;

public class GameServerAuthenticationServer extends AbstractServer{
    private DatabaseConnection databaseConnection;
    private GameServerAuthenticationWrapper gameServerAuthenticationWrapper;

    public GameServerAuthenticationServer(int port, Class className, DatabaseConnection databaseConnection, GameServerAuthenticationWrapper gameServerAuthenticationWrapper) {
        super(port, className);
        this.databaseConnection = databaseConnection;
        this.gameServerAuthenticationWrapper = gameServerAuthenticationWrapper;
    }

    @Override
    protected AbstractUser addHandlersToChannel(ChannelPipeline pipeline) {
        GameServer gameServer = (GameServer) super.addHandlersToChannel(pipeline);
        GameServerReceivers.addReceivers(gameServer, databaseConnection, this);
        return gameServer;
    }

    /*
     *  Receiver methods.
     */

    public void requestUserIdVerification(String gameServerId, String userId){
        gameServerAuthenticationWrapper.requestUserIdVerification(gameServerId, userId);
    }

    public void passUserIdVerificationResult(String gameServerId, String userId, boolean result) {
        InformationExchangeReceiver informationExchangeReceiver = (InformationExchangeReceiver) userHive.getUserById(gameServerId).getPacketReceiver(GameServerReceivers.INFORMATION_RECEIVER);
        if(informationExchangeReceiver != null)
            informationExchangeReceiver.sendUserVerificationResult(userId, result);
        else
            ServerLogger.log("Can't send UserVerificationResult because either gameServerId is wrong or receiver wasn't added to PacketHandler");
    }

    public List<GameServer> getGameServerList(){
        List<GameServer> gameServers = new ArrayList<>();

        for(AbstractUser abstractUser : userHive.getUsers()){
            if(abstractUser.isAuthenticated())
                gameServers.add((GameServer) abstractUser);
        }
        return gameServers;
    }
}

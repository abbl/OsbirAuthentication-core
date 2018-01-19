package pl.bbl.osbir.servers.gameservers.gameserver;

import io.netty.channel.Channel;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.osbir.tools.logger.ServerLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameServer extends AbstractUser{
    private String authenticationKey;
    private Map<String, String> serverInformation;

    public GameServer(String id, Channel channel) {
        super(id, channel);
        serverInformation = new HashMap<>();
    }

    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    public void updateServerInformation(String name, String host, int port){
        serverInformation.put("host", host);
        serverInformation.put("port", Integer.toString(port));
        serverInformation.put("name", name);
        ServerLogger.log("GameServer information updated." + name + " " + host + " " + port);
    }

    public Map<String, String> getServerInformation(){
        return serverInformation;
    }
}

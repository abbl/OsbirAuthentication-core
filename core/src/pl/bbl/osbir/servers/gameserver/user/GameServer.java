package pl.bbl.osbir.servers.gameserver.user;

import io.netty.channel.Channel;
import pl.bbl.network.server.connection.AbstractUser;
import pl.bbl.osbir.tools.logger.ServerLogger;

import java.util.HashMap;

public class GameServer extends AbstractUser{
    private String name;
    private String host;
    private int port;

    public GameServer(String id, Channel channel) {
        super(id, channel);
    }

    public void updateInformation(String name, String host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
        ServerLogger.log("[VerifiedGameServer]updated information with: \n" + name + "\n" + host + "\n" + port + "\n");
    }

    public HashMap<String, String> getInformation(){
        HashMap<String, String> serverInformation = new HashMap<>();
        serverInformation.put("name", name);
        serverInformation.put("host", host);
        serverInformation.put("port", Integer.toString(port));
        return serverInformation;
    }
}

package pl.bbl.osbir;

import pl.bbl.osbir.servers.gameservers.GameServerAuthenticationWrapper;
import pl.bbl.osbir.servers.users.UserAuthenticationWrapper;

public class SegmentCommunicationDirector {
    private GameServerAuthenticationWrapper gameServerAuthenticationWrapper;
    private UserAuthenticationWrapper userAuthenticationWrapper;

    public SegmentCommunicationDirector(){
        gameServerAuthenticationWrapper = new GameServerAuthenticationWrapper();
        userAuthenticationWrapper = new UserAuthenticationWrapper();
    }

    public void getGameServerList(){

    }
}

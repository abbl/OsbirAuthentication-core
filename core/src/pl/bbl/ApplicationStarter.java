package pl.bbl;

import pl.bbl.osbir.SegmentCommunicationDirector;

public class ApplicationStarter {
    public static void main(String args[]){
        SegmentCommunicationDirector segmentCommunicationDirector = new SegmentCommunicationDirector();
        segmentCommunicationDirector.startUserAuthenticationServer();
        segmentCommunicationDirector.startGameServerAuthenticationServer();
    }
}

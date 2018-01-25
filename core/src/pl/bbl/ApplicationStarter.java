package pl.bbl;

import pl.bbl.network.tools.LogType;
import pl.bbl.network.tools.NetworkLogger;
import pl.bbl.osbir.SegmentCommunicationDirector;

public class ApplicationStarter {
    public static void main(String args[]){
        NetworkLogger.changeWorkingMode(LogType.DEBUG);
        SegmentCommunicationDirector segmentCommunicationDirector = new SegmentCommunicationDirector();
    }
}

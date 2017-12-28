package pl.bbl.osbir.tools.logger;

public class ServerLogger {
    public static void log(String message){
        System.out.println("[AuthenticationServer]" + " " + message);
    }
}

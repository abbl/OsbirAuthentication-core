package pl.bbl.database.connection;

public class DatabaseCredentials {
    String host;
    String databaseName;
    String user;
    String password;
    int port;

    public DatabaseCredentials(String host, int port, String databaseName, String user, String password){
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;
    }
}

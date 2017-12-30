package pl.bbl.osbir.database.connection;


import pl.bbl.osbir.tools.logger.ServerLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Bbl on 09.03.2017.
 */
public class DatabaseConnection {
    private DatabaseCredentials DatabaseCredentials;
    private Connection connection;

    public DatabaseConnection(DatabaseCredentials DatabaseCredentials){
        this.DatabaseCredentials = DatabaseCredentials;
        establishConnection();
    }

    private void establishConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + DatabaseCredentials.host + ":" + DatabaseCredentials.port + "/" + DatabaseCredentials.databaseName, DatabaseCredentials.user, DatabaseCredentials.password);
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            ServerLogger.log("Exception in Database class: " + ex);
        }

        if(connection != null)
            ServerLogger.log("Connection established successfully.");
        else
            ServerLogger.log("Connection establishing failed.");
    }

    public Connection getConnection(){
        return connection;
    }
}

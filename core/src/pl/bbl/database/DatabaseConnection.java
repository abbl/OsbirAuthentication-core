package pl.bbl.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Bbl on 09.03.2017.
 */
public class DatabaseConnection {

    /*
    *   Connection credentials.
    **/
    public static class DatabaseCredentials{
        public String host;
        public String databaseName;
        public String user;
        public String password;
        public int port;
    }

    private DatabaseCredentials DatabaseCredentials;

    //Connection itself
    private Connection connection;

    //Logger
    private static final Logger LOGGER = Logger.getLogger( DatabaseConnection.class.getName() );

    /*
    *   Object building.
    **/

    public DatabaseConnection(DatabaseCredentials DatabaseCredentials){
        this.DatabaseCredentials = DatabaseCredentials;
        establishConnection();
    }

    /*
    *   Start of connection.
    **/

    private void establishConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + DatabaseCredentials.host + ":" + DatabaseCredentials.port + "/" + DatabaseCredentials.databaseName, DatabaseCredentials.user, DatabaseCredentials.password);
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            System.out.println("Exception in Database class: " + ex);
        }

        /*Checking if connection was successful.*/
        if(connection != null)
            LOGGER.log(Level.INFO, "Connection established successfully.");
        else
            LOGGER.log(Level.SEVERE, "Connection establishing failed.");
    }

    /*
     * Getters and Setters
     */

    public Connection getConnection(){
        return connection;
    }
}

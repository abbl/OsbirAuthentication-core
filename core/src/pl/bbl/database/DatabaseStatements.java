package pl.bbl.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Bbl on 14.03.2017.
 */
public class DatabaseStatements {

    public static ResultSet queryDatabase(DatabaseConnection databaseConnection, String statement){
        Statement stmt;
        ResultSet rs = null;

        if(databaseConnection.getConnection() != null){
            try {
                stmt = databaseConnection.getConnection().createStatement();
                rs = stmt.executeQuery(statement);
            } catch (SQLException ex) {
                System.out.println("Exception in queryDatabase (Query Class) : " + ex);
            }
        }
        return rs;
    }

    public static void executeStatement(DatabaseConnection databaseConnection, String statement){
        Statement stmt = null;

        if(databaseConnection.getConnection() != null){
            try {
                stmt = databaseConnection.getConnection().createStatement();
                stmt.executeUpdate(statement);
            } catch (SQLException ex) {
                System.out.println("Exception in updateDatabse (Query Class) : " + ex);
            }
        }
    }
}

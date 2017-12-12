package pl.bbl.database.statements.gameservers;

import pl.bbl.database.connection.DatabaseConnection;
import pl.bbl.database.statements.DatabaseStatements;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameServerTableStatements {
    public static ResultSet getGameServerData(DatabaseConnection databaseConnection, String authenticationKey){
        ResultSet resultSet = DatabaseStatements.queryDatabase(databaseConnection, "SELECT * FROM gameservers WHERE BINARY serverAuthenticationKey ='" + authenticationKey + "'");
        try {
            if(resultSet.next()){
                return resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

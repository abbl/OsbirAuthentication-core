package pl.bbl.osbir.database.statements.users;

import pl.bbl.osbir.database.statements.DatabaseStatements;
import pl.bbl.osbir.database.connection.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersTableStatements {
    public static ResultSet getUserData(DatabaseConnection databaseConnection, String login){
        ResultSet resultSet = DatabaseStatements.queryDatabase(databaseConnection,
                "SELECT * FROM users WHERE BINARY login = '" + login + "'");
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

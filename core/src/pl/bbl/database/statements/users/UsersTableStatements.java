package pl.bbl.database.statements.users;

import pl.bbl.database.statements.DatabaseStatements;
import pl.bbl.database.connection.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersTableStatements {
    public static ResultSet getUserData(DatabaseConnection databaseConnection, String login){
        ResultSet resultSet = DatabaseStatements.queryDatabase(databaseConnection,
                "SELECT * FROM users WHERE login = '" + login + "'");
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
package com.example.personnel.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection connectDatabase() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/personnel","root","");
    }
}

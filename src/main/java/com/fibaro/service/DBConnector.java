package com.fibaro.service;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public DBConnector() {
    }


    public static Connection getConnection() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.50.173:1521:bpsc",
                "raporty","raporty");
                return conn;
    }
}

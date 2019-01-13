package com.training.carfactory.model.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carfactory","root", "root");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection is failed");
        }
        return connection;
    }
}

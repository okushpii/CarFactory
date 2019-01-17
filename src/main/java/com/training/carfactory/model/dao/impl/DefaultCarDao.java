package com.training.carfactory.model.dao.impl;

import com.training.carfactory.model.dao.CarDao;
import com.training.carfactory.model.dao.util.ConnectionFactory;
import com.training.carfactory.model.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DefaultCarDao implements CarDao {

    private ConnectionFactory connectionFactory;

    public DefaultCarDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }



    @Override
    public void addCar(Car car) {

        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement prst = connection.prepareStatement("INSERT INTO car(body_id, engine_id, wheels_id, customer, status, price)" +
                    "VALUES(?, ?, ?, ?, ?, ?)");

            prst.setLong(1, car.getBody().getId());
            prst.setLong(2, car.getEngine().getId());
            prst.setLong(3, car.getWheels().getId());
            prst.setString(4, car.getCustomer());
            prst.setString(5, car.getStatus().toString());
            prst.setLong(6, car.getPrice());
            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
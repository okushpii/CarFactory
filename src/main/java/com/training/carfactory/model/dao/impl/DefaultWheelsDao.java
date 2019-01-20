package com.training.carfactory.model.dao.impl;

import com.training.carfactory.model.dao.WheelsDao;
import com.training.carfactory.model.dao.util.ConnectionFactory;
import com.training.carfactory.model.entity.Wheels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultWheelsDao implements WheelsDao {

    private ConnectionFactory connectionFactory;

    public DefaultWheelsDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Wheels> getAll() {
        List<Wheels> wheelsList = new ArrayList<>();
        try(Connection connection = connectionFactory.getConnection()){
            PreparedStatement prst = connection.prepareStatement("SELECT * FROM wheels");
            ResultSet rs = prst.executeQuery();
            while (rs.next()){
                Wheels wheels = new Wheels();
                wheels.setId(rs.getLong(1));
                wheels.setName(rs.getString(2));
                wheels.setSize(rs.getLong(3));
                wheels.setPrice(rs.getLong(4));
                wheelsList.add(wheels);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wheelsList;
    }

    @Override
    public void addWheels(Wheels wheels) {
        try(Connection connection = connectionFactory.getConnection()){
            PreparedStatement prst = connection.prepareStatement("INSERT INTO wheels(name, size, price)" +
                    "VALUES(?, ?, ?)");

            prst.setString(1, wheels.getName());
            prst.setLong(2, wheels.getSize());
            prst.setLong(3, wheels.getPrice());

            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

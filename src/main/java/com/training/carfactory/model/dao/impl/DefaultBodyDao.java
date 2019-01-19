package com.training.carfactory.model.dao.impl;

import com.training.carfactory.model.dao.BodyDao;
import com.training.carfactory.model.dao.util.ConnectionFactory;
import com.training.carfactory.model.entity.Body;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultBodyDao implements BodyDao {

    private ConnectionFactory connectionFactory;

    public DefaultBodyDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Body> getAll() {
        List<Body> bodies = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement prst = connection.prepareStatement("SELECT * FROM body");
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Body body = new Body();
                body.setId(rs.getLong(1));
                body.setName(rs.getString(2));
                body.setType(Body.Type.valueOf(rs.getString(3)));
                body.setPrice(rs.getLong(4));
                bodies.add(body);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bodies;
    }

    @Override
    public Body getByName(String name) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement prst = connection.prepareStatement("SELECT * FROM body WHERE name = ?");
            prst.setString(1, name);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                Body body = new Body();
                body.setId(rs.getLong(1));
                body.setName(rs.getString(2));
                body.setType(Body.Type.valueOf(rs.getString(3)));
                body.setPrice(rs.getLong(4));
                return body;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addBody(Body body) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement prst = connection.prepareStatement("INSERT INTO body(name, type, price)" +
                    "VALUES(?, ?, ?)");

            prst.setString(1, body.getName());
            prst.setString(2, body.getType().toString());
            prst.setLong(3, body.getPrice());
            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


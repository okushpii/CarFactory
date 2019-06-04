package com.training.carfactory.model.dao.impl;

import com.training.carfactory.model.dao.SalonDao;
import com.training.carfactory.model.dao.util.ConnectionFactory;
import com.training.carfactory.model.entity.Salon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultSalonDao implements SalonDao {

    private ConnectionFactory connectionFactory;

    public DefaultSalonDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Salon> getAll() {
        List<Salon> salons = new ArrayList<>();
        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement prst = connection.prepareStatement("SELECT * FROM salon");
            ResultSet rs = prst.executeQuery();
            while (rs.next()){
                Salon salon = new Salon();
                salon.setId(rs.getLong(1));
                salon.setName(rs.getString(2));
                salon.setPrice(rs.getLong(3));
                salon.setImageUrl(rs.getString(4));
                salons.add(salon);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return salons;
    }

    @Override
    public Salon getByName(String name) {
        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement prst =  connection.prepareStatement("SELECT * FROM salon WHERE name = ?");
            prst.setString(1, name);
            ResultSet rs = prst.executeQuery();
            if (rs.next()){
                Salon salon = new Salon();
                salon.setId(rs.getLong(1));
                salon.setName(rs.getString(2));
                salon.setPrice(rs.getLong(3));
                salon.setImageUrl(rs.getString(4));
                return salon;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.training.carfactory.model.dao.impl;

import com.training.carfactory.model.dao.EngineDao;
import com.training.carfactory.model.dao.util.ConnectionFactory;
import com.training.carfactory.model.entity.Engine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultEngineDao implements EngineDao {

    private ConnectionFactory connectionFactory;

    public DefaultEngineDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Engine> getAll() {
        List<Engine> engines = new ArrayList<>();
        try(Connection connection = connectionFactory.getConnection()){
            PreparedStatement prst = connection.prepareStatement("SELECT * FROM engine");
            ResultSet rs = prst.executeQuery();
            while (rs.next()){
                Engine engine = new Engine();
                engine.setId(rs.getLong(1));
                engine.setName(rs.getString(2));
                engine.setPower(rs.getLong(3));
                engine.setPrice(rs.getLong(4));
                engine.setVolume(rs.getLong(5));
                engines.add(engine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return engines;
    }
}

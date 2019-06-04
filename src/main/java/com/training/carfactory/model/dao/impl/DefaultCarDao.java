package com.training.carfactory.model.dao.impl;

import com.training.carfactory.model.dao.CarDao;
import com.training.carfactory.model.dao.util.ConnectionFactory;
import com.training.carfactory.model.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DefaultCarDao implements CarDao {

    private ConnectionFactory connectionFactory;

    public DefaultCarDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement prst = connection.prepareStatement("SELECT * FROM car AS c" +
                    " INNER JOIN body AS b ON c.body_id = b.id" +
                    " INNER JOIN engine AS e ON c.engine_id = e.id" +
                    " INNER JOIN wheels AS w ON c.wheels_id = w.id" +
                    " INNER JOIN salon AS s ON c.salon_id = s.id ");
            ResultSet rs = prst.executeQuery();
            while (rs.next()){
                Car car = new Car();
                car.setId(rs.getLong("id"));
                Body body = new Body();
                body.setName(rs.getString("b.name"));
                body.setType(Body.Type.valueOf(rs.getString("b.type")));
                Engine engine = new Engine();
                engine.setName(rs.getString("e.name"));
                engine.setVolume(rs.getLong("e.volume"));
                engine.setPower(rs.getLong("e.power"));
                Wheels wheels = new Wheels();
                wheels.setName(rs.getString("w.name"));
                wheels.setSize(rs.getString("w.size"));
                Salon salon = new Salon();
                salon.setName(rs.getString("s.name"));
                car.setBody(body);
                car.setEngine(engine);
                car.setWheels(wheels);
                car.setSalon(salon);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public void addCar(Car car) {

        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement prst = connection.prepareStatement("INSERT INTO car(body_id, engine_id, wheels_id, salon_id, customer, status, price)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)");

            prst.setLong(1, car.getBody().getId());
            prst.setLong(2, car.getEngine().getId());
            prst.setLong(3, car.getWheels().getId());
            prst.setLong(4, car.getSalon().getId());
            prst.setString(5, car.getCustomer());
            prst.setString(6, car.getStatus().toString());
            prst.setLong(7, car.getPrice());
            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
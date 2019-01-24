package com.training.carfactory.model.service.impl;

import com.training.carfactory.model.dao.CarDao;
import com.training.carfactory.model.entity.Body;
import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.entity.Engine;
import com.training.carfactory.model.entity.Wheels;
import com.training.carfactory.model.service.CarService;

import java.util.ArrayList;
import java.util.List;

public class DefaultCarService implements CarService {

    private CarDao carDao;

    public DefaultCarService(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public void addCar(Car car) {
        carDao.addCar(car);
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        Car car = new Car();
        Body body = new Body();
        Engine engine = new Engine();
        Wheels wheels = new Wheels();
        body.setName("Nick");
        engine.setName("Alexander");
        wheels.setName("Victor");
        car.setId(1L);
        car.setBody(body);
        car.setEngine(engine);
        car.setWheels(wheels);
        cars.add(car);
        return cars;
    }
}

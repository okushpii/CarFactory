package com.training.carfactory.model.service.impl;

import com.training.carfactory.model.dao.CarDao;
import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.service.CarService;

public class DefaultCarService implements CarService {

    private CarDao carDao;

    public DefaultCarService(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public void addCar(Car car) {
        carDao.addCar(car);
    }

}

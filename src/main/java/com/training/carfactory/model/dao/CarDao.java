package com.training.carfactory.model.dao;

import com.training.carfactory.model.entity.Car;

import java.util.List;

public interface CarDao {

    List<Car> getAll();

    void addCar(Car car);

}

package com.training.carfactory.model.service;

import com.training.carfactory.model.entity.Car;

import java.util.List;

public interface CarService {

    void addCar(Car car);

    List<Car> getAll();
}

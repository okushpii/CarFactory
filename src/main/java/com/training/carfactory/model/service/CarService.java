package com.training.carfactory.model.service;

import com.training.carfactory.model.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> getAll();

    void addCar(Car car);
}

package com.training.carfactory.controller.context;

import com.training.carfactory.model.entity.Car;

public class CarContext {

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

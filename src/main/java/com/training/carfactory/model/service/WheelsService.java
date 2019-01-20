package com.training.carfactory.model.service;

import com.training.carfactory.model.entity.Wheels;

import java.util.List;

public interface WheelsService {

    List<Wheels> getAll();

    void addWheels(Wheels wheels);
}

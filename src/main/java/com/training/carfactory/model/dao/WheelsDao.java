package com.training.carfactory.model.dao;

import com.training.carfactory.model.entity.Wheels;

import java.util.List;

public interface WheelsDao {

    List<Wheels> getAll();

    void addWheels(Wheels wheels);
}

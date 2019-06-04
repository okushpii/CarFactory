package com.training.carfactory.model.dao;

import com.training.carfactory.model.entity.Salon;

import java.util.List;

public interface SalonDao {

    List<Salon> getAll();

    Salon getByName(String name);
}

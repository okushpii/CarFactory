package com.training.carfactory.model.service;

import com.training.carfactory.model.entity.Salon;

import java.util.List;

public interface SalonService {

    Salon getByName(String name);

    List<Salon> getAll();

}

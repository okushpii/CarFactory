package com.training.carfactory.model.service;

import com.training.carfactory.model.entity.Engine;

import java.util.List;

public interface EngineService {

    List<Engine> getAll();

    Engine getByName(String name);
}

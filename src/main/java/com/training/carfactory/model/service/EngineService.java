package com.training.carfactory.model.service;

import com.training.carfactory.model.entity.Engine;

import java.util.List;

public interface EngineService extends PartService<Engine>{

    List<Engine> getAll();
}

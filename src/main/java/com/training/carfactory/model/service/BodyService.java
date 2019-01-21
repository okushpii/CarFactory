package com.training.carfactory.model.service;

import com.training.carfactory.model.entity.Body;

import java.util.List;

public interface BodyService extends PartService<Body>{

    List<Body> getAll();
}

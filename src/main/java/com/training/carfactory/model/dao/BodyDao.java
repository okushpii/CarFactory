package com.training.carfactory.model.dao;

import com.training.carfactory.model.entity.Body;

import java.util.List;

public interface BodyDao {

    List<Body> getAll();

    Body getByName(String name);
}

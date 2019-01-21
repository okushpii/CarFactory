package com.training.carfactory.model.service;

import com.training.carfactory.model.entity.Part;

public interface PartService<T extends Part> {

    T getByName(String name);
}

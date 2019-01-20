package com.training.carfactory.model.service.impl;

import com.training.carfactory.model.dao.BodyDao;
import com.training.carfactory.model.entity.Body;
import com.training.carfactory.model.service.BodyService;

import java.util.List;

public class DefaultBodyService implements BodyService {

    private BodyDao bodyDao;

    public DefaultBodyService(BodyDao bodyDao) {
        this.bodyDao = bodyDao;
    }

    @Override
    public List<Body> getAll() {
        return bodyDao.getAll();
    }

    @Override
    public Body getByName(String name) {
        return bodyDao.getByName(name);
    }

    public void addBody(Body body) {
        bodyDao.addBody(body);
    }

}

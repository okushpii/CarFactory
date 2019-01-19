package com.training.carfactory.model.service.impl;

import com.training.carfactory.model.dao.EngineDao;
import com.training.carfactory.model.entity.Engine;
import com.training.carfactory.model.service.EngineService;

import java.util.List;

public class DefaultEngineService implements EngineService {

    private EngineDao engineDao;

    public DefaultEngineService(EngineDao engineDao) {
        this.engineDao = engineDao;
    }

    @Override
    public List<Engine> getAll() {
        return engineDao.getAll();
    }
}

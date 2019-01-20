package com.training.carfactory.model.service.impl;

import com.training.carfactory.model.dao.WheelsDao;
import com.training.carfactory.model.entity.Wheels;
import com.training.carfactory.model.service.WheelsService;

import java.util.List;

public class DefaultWheelsService implements WheelsService {

    private WheelsDao wheelsDao;

    public DefaultWheelsService(WheelsDao wheelsDao) { this.wheelsDao = wheelsDao; }


    @Override
    public List<Wheels> getAll() {
        return wheelsDao.getAll();
    }

    @Override
    public void addWheels(Wheels wheels) {
        wheelsDao.addWheels(wheels);
    }
}

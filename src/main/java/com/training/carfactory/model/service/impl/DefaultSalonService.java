package com.training.carfactory.model.service.impl;

import com.training.carfactory.model.dao.SalonDao;
import com.training.carfactory.model.entity.Salon;
import com.training.carfactory.model.service.SalonService;

import java.util.List;

public class DefaultSalonService implements SalonService {

    private SalonDao salonDao;

    public DefaultSalonService(SalonDao salonDao) {
        this.salonDao = salonDao;
    }

    @Override
    public List<Salon> getAll() {
        return salonDao.getAll();
    }

    @Override
    public Salon getByName(String name) {
        return salonDao.getByName(name);
    }
}

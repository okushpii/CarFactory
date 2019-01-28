package com.training.carfactory.model.service.impl.util;

import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.service.BodyService;
import com.training.carfactory.model.service.EngineService;
import javafx.scene.control.Button;

import java.util.concurrent.CountDownLatch;


public class CarPropertyUpdater {

    private BodyService bodyService;
    private EngineService engineService;

    public CarPropertyUpdater(BodyService bodyService, EngineService engineService) {
        this.bodyService = bodyService;
        this.engineService = engineService;
    }

    public void updateAfterBodyInstall(Car car, String selectedBody, Button btn, CountDownLatch cdl) {
        new Thread(() -> {
            await(cdl);
            car.setBody(bodyService.getByName(selectedBody));
            car.setStatus(Car.Status.IN_PROGRESS);
            btn.setDisable(false);
        }).start();
    }

    public void updateAfterBodyRemove(Car car, Button btn, CountDownLatch cdl){
        new Thread(() -> {
            await(cdl);
            car.setBody(null);
            car.setStatus(Car.Status.NEW);
            btn.setDisable(false);
        }).start();
    }

    public void updateAfterEngineInstall(Car car, String selectedEngine, Button btn, CountDownLatch cdl) {
        new Thread(() -> {
            await(cdl);
            car.setEngine(engineService.getByName(selectedEngine));
            btn.setDisable(false);
        }).start();
    }

    public void updateAfterEngineRemove(Car car, Button btn, CountDownLatch cdl){
        new Thread(() -> {
            await(cdl);
            car.setEngine(null);
            btn.setDisable(false);
        }).start();
    }

    private void await(CountDownLatch cdl) {
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

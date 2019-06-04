package com.training.carfactory.model.service.impl.util;

import com.training.carfactory.controller.context.CarContext;
import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.entity.Engine;
import com.training.carfactory.model.entity.Salon;
import com.training.carfactory.model.entity.Wheels;
import com.training.carfactory.model.service.*;
import com.training.carfactory.model.exception.IncorrectPartException;
import com.training.carfactory.model.service.BodyService;
import com.training.carfactory.model.service.CarService;
import com.training.carfactory.model.service.EngineService;
import com.training.carfactory.model.service.WheelsService;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.concurrent.CountDownLatch;


public class CarPropertyUpdater {

    private BodyService bodyService;
    private EngineService engineService;
    private WheelsService wheelsService;
    private SalonService salonService;
    private CarService carService;
    private ValueFormatterService valueFormatterService;
    private PriceCalculationService priceCalculationService;
    private CarContext carContext;

    public CarPropertyUpdater(BodyService bodyService, EngineService engineService,
                              WheelsService wheelsService, SalonService salonService, ValueFormatterService valueFormatterService,
                              CarService carService, PriceCalculationService priceCalculationService, CarContext carContext) {
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.wheelsService = wheelsService;
        this.salonService = salonService;
        this.valueFormatterService = valueFormatterService;
        this.carService = carService;
        this.priceCalculationService = priceCalculationService;
        this.carContext = carContext;
    }

    public void updateAfterBodyInstall(String selectedBody, Button btn, Label bodyCarLabel, CountDownLatch cdl) {
        new Thread(() -> {
            await(cdl);
            carContext.getCar().setBody(bodyService.getByName(selectedBody));
            carContext.getCar().setStatus(Car.Status.IN_PROGRESS);
            btn.setDisable(false);
            Platform.runLater(() -> bodyCarLabel.setText(valueFormatterService.formatBody(carContext.getCar().getBody())));
        }).start();
    }

    public void updateAfterBodyRemove(Button btn, Label bodyCarLabel, CountDownLatch cdl) {
        new Thread(() -> {
            await(cdl);
            carContext.getCar().setBody(null);
            carContext.getCar().setStatus(Car.Status.NEW);
            btn.setDisable(false);
            Platform.runLater(() -> bodyCarLabel.setText(Messages.NOT_INSTALLED));
        }).start();
    }

    public void updateAfterEngineInstall(String selectedEngine, Button btn, Label engineCarLabel, CountDownLatch cdl) {
        Engine engine = engineService.getByName(selectedEngine);
        verifyEngineAndBodyAreComparable(engine);
        new Thread(() -> {
            await(cdl);
            carContext.getCar().setEngine(engine);
            carContext.getCar().setStatus(Car.Status.IN_PROGRESS);
            btn.setDisable(false);
            Platform.runLater(() -> engineCarLabel.setText(valueFormatterService.formatEngine(carContext.getCar().getEngine())));
        }).start();
    }

    private void verifyEngineAndBodyAreComparable(Engine engine) {
        if(engine.getVolume() < carContext.getCar().getBody().getMinEngineVolume() ||
                engine.getVolume() > carContext.getCar().getBody().getMaxEngineVolume()){
            throw new IncorrectPartException(Messages.ENGINE_WAS_NOT_SUPPORTED);
        }
    }

    public void updateAfterEngineRemove(Button btn, Label engineCarLabel, CountDownLatch cdl) {
        new Thread(() -> {
            await(cdl);
            carContext.getCar().setEngine(null);
            carContext.getCar().setStatus(Car.Status.NEW);
            btn.setDisable(false);
            Platform.runLater(() -> engineCarLabel.setText(Messages.NOT_INSTALLED));
        }).start();
    }

    public void updateAfterWheelsInstall(String selectedWheels, Button btn, Label wheelsCarLabel, CountDownLatch cdl) {
        new Thread(() -> {
            await(cdl);
            Wheels wheels = wheelsService.getByName(selectedWheels);
            carContext.getCar().setWheels(wheels);
            carContext.getCar().setStatus(Car.Status.IN_PROGRESS);
            btn.setDisable(false);
            Platform.runLater(() -> wheelsCarLabel.setText(valueFormatterService.formatWheels(carContext.getCar().getWheels())));
        }).start();
    }

    public void updateAfterWheelsRemove(Button btn, Label wheelsCarLabel, CountDownLatch cdl) {
        new Thread(() -> {
            await(cdl);
            carContext.getCar().setWheels(null);
            carContext.getCar().setStatus(Car.Status.NEW);
            btn.setDisable(false);
            Platform.runLater(() -> wheelsCarLabel.setText(Messages.NOT_INSTALLED));
        }).start();
    }

    public void updateAfterSalonInstall(String selectedSalon, Button btn, Label salonCarLabel, CountDownLatch cdl) {
        new Thread(() -> {
            await(cdl);
            Salon salon = salonService.getByName(selectedSalon);
            carContext.getCar().setSalon(salon);
            carContext.getCar().setStatus(Car.Status.IN_PROGRESS);
            btn.setDisable(false);
            Platform.runLater(() -> salonCarLabel.setText(valueFormatterService.formatSalon(carContext.getCar().getSalon())));
        }).start();
    }

    public void updateAfterSalonRemove(Button btn, Label salonCarLabel, CountDownLatch cdl) {
        new Thread(() -> {
            await(cdl);
            carContext.getCar().setSalon(null);
            carContext.getCar().setStatus(Car.Status.NEW);
            btn.setDisable(false);
            Platform.runLater(() -> salonCarLabel.setText(Messages.NOT_INSTALLED));
        }).start();
    }

    public void updateAfterCarInstall(CountDownLatch cdl) {
        new Thread(() -> {
            await(cdl);
            Car car = carContext.getCar();
            car.setCustomer(CarProperties.CUSTOMER);
            car.setStatus(Car.Status.DONE);
            car.setPrice(priceCalculationService.calculatePrice(CarProperties.INITIAL_PRICE,
                    car.getBody().getPrice(), car.getEngine().getPrice(), car.getWheels().getPrice(), car.getSalon().getPrice()));
            carService.addCar(car);
            carContext.setCar(null);
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

package com.training.carfactory.controller.facade;

import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.service.BodyService;
import com.training.carfactory.model.service.CarService;
import com.training.carfactory.model.service.EngineService;
import com.training.carfactory.model.service.WheelsService;
import com.training.carfactory.model.service.impl.PriceCalculationService;
import javafx.scene.control.ComboBox;


public class CarFacade {

    private BodyService bodyService;
    private EngineService engineService;
    private WheelsService wheelsService;
    private CarService carService;
    private PriceCalculationService priceCalculationService;

    private Car car;

    public CarFacade(BodyService bodyService, EngineService engineService,
                     WheelsService wheelsService, CarService carService, PriceCalculationService priceCalculationService) {
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.wheelsService = wheelsService;
        this.carService = carService;
        this.priceCalculationService = priceCalculationService;
    }

    public void buildBody(ComboBox<String> bodies){
        checkIfCarPresent();
        car.setStatus(Car.Status.NEW);
        car.setBody(bodyService.getByName(bodies.getValue()));
    }

    public void buildEngine(ComboBox<String> engines){
        car.setStatus(Car.Status.IN_PROGRESS);
        car.setEngine(engineService.getByName(engines.getValue()));
    }

    public void buildWheels(ComboBox<String> wheels){
        car.setWheels(wheelsService.getByName(wheels.getValue()));
    }

    public void finishCar(){
        car.setCustomer("DEFAULT");
        car.setStatus(Car.Status.DONE);
        car.setPrice(priceCalculationService.calculatePrice(5000L,
                car.getBody().getPrice(), car.getEngine().getPrice(), car.getWheels().getPrice()));
        carService.addCar(car);
    }

    private void checkIfCarPresent() {
        if (car == null){
            car = new Car();
        }
    }
}

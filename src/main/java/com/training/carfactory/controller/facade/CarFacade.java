package com.training.carfactory.controller.facade;

import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.entity.Part;
import com.training.carfactory.model.exception.PartIsMissingException;
import com.training.carfactory.model.service.*;
import com.training.carfactory.model.service.impl.util.PriceCalculationService;
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
        if (bodies.getValue() != null)
        car.setBody(bodyService.getByName(bodies.getValue()));
    }

    public void buildEngine(ComboBox<String> engines){
        car.setStatus(Car.Status.IN_PROGRESS);
        if (engines.getValue() != null)
        car.setEngine(engineService.getByName(engines.getValue()));
    }

    public void buildWheels(ComboBox<String> wheels){
        if (wheels.getValue() != null)
        car.setWheels(wheelsService.getByName(wheels.getValue()));
    }

    public void finishCar(){
        validateCar(car.getBody(), car.getEngine(), car.getWheels());
        car.setCustomer("DEFAULT");
        car.setStatus(Car.Status.DONE);
        car.setPrice(priceCalculationService.calculatePrice(5000L,
                car.getBody().getPrice(), car.getEngine().getPrice(), car.getWheels().getPrice()));
        carService.addCar(car);
    }

    private void validateCar(Part...parts){
        for (Part part : parts){
            validatePart(part);
        }
    }

    private void validatePart(Part part) {
        if (part == null){
            throw new PartIsMissingException();
        }
    }

    private void checkIfCarPresent() {
        if (car == null){
            car = new Car();
        }
    }
}

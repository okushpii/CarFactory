package com.training.carfactory.controller.facade;

import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.entity.Part;
import com.training.carfactory.model.exception.PartIsMissingException;
import com.training.carfactory.model.service.*;
import com.training.carfactory.model.service.impl.util.PriceCalculationService;
import com.training.carfactory.model.service.impl.util.ProgressBarSimulator;
import javafx.scene.control.*;


public class CarFacade {

    private static final long INITIAL_PRICE = 5000L;
    private static final int BODY_ASSEMBLE_DELAY = 30;
    private static final int BODY_RESEMBLE_DELAY = 20;
    private static final String CUSTOMER = "DEFAULT";

    private BodyService bodyService;
    private EngineService engineService;
    private WheelsService wheelsService;
    private CarService carService;
    private PriceCalculationService priceCalculationService;
    private ProgressBarSimulator progressBarSimulator;

    private Car car;

    public CarFacade(BodyService bodyService, EngineService engineService,
                     WheelsService wheelsService, CarService carService,
                     PriceCalculationService priceCalculationService, ProgressBarSimulator progressBarSimulator) {
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.wheelsService = wheelsService;
        this.carService = carService;
        this.priceCalculationService = priceCalculationService;
        this.progressBarSimulator = progressBarSimulator;
    }

    public void buildBody(ListView<String> bodies, ProgressBar bodyProgressBar,
                          Button installButton, Button removeButton){
        checkIfCarPresent();
        String selectedItem = bodies.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            progressBarSimulator.simulateProgress(bodyProgressBar, BODY_ASSEMBLE_DELAY, removeButton);
            car.setBody(bodyService.getByName(selectedItem));
            car.setStatus(Car.Status.IN_PROGRESS);
            installButton.setDisable(true);
        } else {
            throw new PartIsMissingException("Body is not chosen");
        }
    }

    public void removeBody(ProgressBar bodyProgress, Button installBodyButton, Button removeBodyButton) {
        progressBarSimulator.simulateDownTimeProgress(bodyProgress, BODY_RESEMBLE_DELAY, installBodyButton);
        removeBodyButton.setDisable(true);
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
        car.setCustomer(CUSTOMER);
        car.setStatus(Car.Status.DONE);
        car.setPrice(priceCalculationService.calculatePrice(INITIAL_PRICE,
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
            throw new PartIsMissingException("Some part is missing");
        }
    }

    private void checkIfCarPresent() {
        if (car == null){
            car = new Car();
        }
    }
}

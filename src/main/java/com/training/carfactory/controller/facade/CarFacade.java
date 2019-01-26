package com.training.carfactory.controller.facade;

import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.exception.PartIsMissingException;
import com.training.carfactory.model.service.BodyService;
import com.training.carfactory.model.service.CarService;
import com.training.carfactory.model.service.EngineService;
import com.training.carfactory.model.service.WheelsService;
import com.training.carfactory.model.service.impl.util.CarProperties;
import com.training.carfactory.model.service.impl.util.PartVerifier;
import com.training.carfactory.model.service.impl.util.PriceCalculationService;
import com.training.carfactory.model.service.impl.util.ProgressBarSimulator;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;


public class CarFacade {

    private BodyService bodyService;
    private EngineService engineService;
    private WheelsService wheelsService;
    private CarService carService;
    private PriceCalculationService priceCalculationService;
    private ProgressBarSimulator progressBarSimulator;
    private PartVerifier partVerifier;

    private Car car;

    public CarFacade(BodyService bodyService, EngineService engineService,
                     WheelsService wheelsService, CarService carService,
                     PriceCalculationService priceCalculationService,
                     ProgressBarSimulator progressBarSimulator, PartVerifier partVerifier) {
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.wheelsService = wheelsService;
        this.carService = carService;
        this.priceCalculationService = priceCalculationService;
        this.progressBarSimulator = progressBarSimulator;
        this.partVerifier = partVerifier;
    }

    public void buildBody(ListView<String> bodies, ProgressBar bodyProgressBar,
                          Button installButton, Button removeButton){
        checkIfCarPresent();
        String selectedItem = bodies.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            progressBarSimulator.simulateProgress(bodyProgressBar, CarProperties.BODY_ASSEMBLE_DELAY, removeButton);
            car.setBody(bodyService.getByName(selectedItem));
            car.setStatus(Car.Status.IN_PROGRESS);
            installButton.setDisable(true);
        } else {
            throw new PartIsMissingException("Body is not chosen");
        }
    }

    public void removeBody(ProgressBar bodyProgress, Button installBodyButton, Button removeBodyButton) {
        partVerifier.verifyPartAbsent(car.getWheels(), "Wheels should be removed first");
        partVerifier.verifyPartAbsent(car.getEngine(), "Engine should be removed first");
        progressBarSimulator.simulateDownTimeProgress(bodyProgress, CarProperties.BODY_RESEMBLE_DELAY, installBodyButton);
        car.setBody(null);
        removeBodyButton.setDisable(true);
    }

    public void buildEngine(ListView<String> engines, ProgressBar engineProgressBar,
                            Button installButton, Button removeButton){
        checkIfCarPresent();
        String selectedItem = engines.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            partVerifier.verifyPartPresent(car.getBody(), "Body was not installed");
            progressBarSimulator.simulateProgress(engineProgressBar, CarProperties.ENGINE_ASSEMBLE_DELAY, removeButton);
            car.setEngine(engineService.getByName(selectedItem));
            installButton.setDisable(true);
        } else {
            throw new PartIsMissingException("Engine is not chosen");
        }
    }

    public void removeEngine(ProgressBar engineProgress, Button installEngineButton, Button removeEngineButton) {
        progressBarSimulator.simulateDownTimeProgress(engineProgress, CarProperties.ENGINE_RESEMBLE_DELAY, installEngineButton);
        car.setEngine(null);
        removeEngineButton.setDisable(true);
    }

    public void buildWheels(ComboBox<String> wheels){
        if (wheels.getValue() != null)
        car.setWheels(wheelsService.getByName(wheels.getValue()));
    }

    public void finishCar(){
        partVerifier.verifyPartsPresent(car.getBody(), car.getEngine(), car.getWheels());
        car.setCustomer(CarProperties.CUSTOMER);
        car.setStatus(Car.Status.DONE);
        car.setPrice(priceCalculationService.calculatePrice(CarProperties.INITIAL_PRICE,
                car.getBody().getPrice(), car.getEngine().getPrice(), car.getWheels().getPrice()));
        carService.addCar(car);
    }

    private void checkIfCarPresent() {
        if (car == null){
            car = new Car();
        }
    }
}

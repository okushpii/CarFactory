package com.training.carfactory.controller.facade;

import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.exception.PartIsMissingException;
import com.training.carfactory.model.service.BodyService;
import com.training.carfactory.model.service.CarService;
import com.training.carfactory.model.service.EngineService;
import com.training.carfactory.model.service.WheelsService;
import com.training.carfactory.model.service.impl.util.*;
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
    private PartVerifier partVerifier;
    private CarProgressService carProgressService;

    private Car car;

    public CarFacade(BodyService bodyService, EngineService engineService,
                     WheelsService wheelsService, CarService carService,
                     PriceCalculationService priceCalculationService, PartVerifier partVerifier, CarProgressService carProgressService) {
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.wheelsService = wheelsService;
        this.carService = carService;
        this.priceCalculationService = priceCalculationService;
        this.partVerifier = partVerifier;
        this.carProgressService = carProgressService;
    }

    public void buildBody(ListView<String> bodies, ProgressBar bodyProgressBar,
                          Button installButton, Button removeButton) {
        checkIfCarPresent();
        String selectedItem = bodies.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            carProgressService.installBody(bodyProgressBar, car, selectedItem, installButton, removeButton);
        } else {
            throw new PartIsMissingException("Body is not chosen");
        }
    }

    public void removeBody(ProgressBar bodyProgress, Button installBodyButton, Button removeBodyButton) {
        partVerifier.verifyPartAbsent(car.getWheels(), "Wheels should be removed first");
        partVerifier.verifyPartAbsent(car.getEngine(), "Engine should be removed first");
        carProgressService.removeBody(bodyProgress, car, installBodyButton, removeBodyButton);
    }

    public void buildEngine(ListView<String> engines, ProgressBar engineProgressBar,
                            Button installButton, Button removeButton) {
        checkIfCarPresent();
        String selectedItem = engines.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            partVerifier.verifyPartPresent(car.getBody(), "Body was not installed");
            carProgressService.installEngine(engineProgressBar, car, selectedItem, installButton, removeButton);
        } else {
            throw new PartIsMissingException("Engine is not chosen");
        }
    }

    public void removeEngine(ProgressBar engineProgress, Button installEngineButton, Button removeEngineButton) {
        carProgressService.removeEngine(engineProgress, car, installEngineButton, removeEngineButton);
    }

    public void buildWheels(ComboBox<String> wheels) {
        if (wheels.getValue() != null)
            car.setWheels(wheelsService.getByName(wheels.getValue()));
    }

    public void finishCar() {
        partVerifier.verifyPartsPresent(car.getBody(), car.getEngine(), car.getWheels());
        car.setCustomer(CarProperties.CUSTOMER);
        car.setStatus(Car.Status.DONE);
        car.setPrice(priceCalculationService.calculatePrice(CarProperties.INITIAL_PRICE,
                car.getBody().getPrice(), car.getEngine().getPrice(), car.getWheels().getPrice()));
        carService.addCar(car);
    }

    private void checkIfCarPresent() {
        if (car == null) {
            car = new Car();
        }
    }
}

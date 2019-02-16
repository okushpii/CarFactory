package com.training.carfactory.controller.facade;

import com.training.carfactory.controller.context.CarContext;
import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.exception.PartIsMissingException;
import com.training.carfactory.model.service.impl.util.*;
import javafx.scene.control.*;


public class CarFacade {

    private PartVerifier partVerifier;
    private CarProgressService carProgressService;

    private CarContext carContext;

    public CarFacade(PartVerifier partVerifier, CarProgressService carProgressService, CarContext carContext) {
        this.partVerifier = partVerifier;
        this.carContext = carContext;
        this.carProgressService = carProgressService;
    }

    public void buildBody(ListView<String> bodies, ProgressBar bodyProgressBar,
                          Button installButton, Label carBodyLabel, Button removeButton) {
        checkIfCarPresent();
        String selectedItem = bodies.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            carProgressService.installBody(bodyProgressBar, selectedItem, carBodyLabel, installButton, removeButton);
        } else {
            throw new PartIsMissingException(Messages.BODY_IS_NOT_CHOSEN);
        }
    }

    public void removeBody(ProgressBar bodyProgress, Button installBodyButton, Label carBodyLabel, Button removeBodyButton) {
        partVerifier.verifyPartAbsent(carContext.getCar().getWheels(), Messages.WHEELS_SHOULD_BE_REMOVED_FIRST);
        partVerifier.verifyPartAbsent(carContext.getCar().getEngine(), Messages.ENGINE_SHOULD_BE_REMOVED_FIRST);
        carProgressService.removeBody(bodyProgress, installBodyButton, carBodyLabel, removeBodyButton);
    }

    public void buildEngine(ListView<String> engines, ProgressBar engineProgressBar,
                            Button installButton, Label carEngineLabel, Button removeButton) {
        checkIfCarPresent();
        String selectedItem = engines.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            partVerifier.verifyPartPresent(carContext.getCar().getBody(), Messages.BODY_WAS_NOT_INSTALLED);
            carProgressService.installEngine(engineProgressBar, selectedItem, carEngineLabel, installButton, removeButton);
        } else {
            throw new PartIsMissingException(Messages.ENGINE_IS_NOT_CHOSEN);
        }
    }

    public void removeEngine(ProgressBar engineProgress, Button installEngineButton, Label carEngineLabel, Button removeEngineButton) {
        carProgressService.removeEngine(engineProgress, installEngineButton, carEngineLabel, removeEngineButton);
    }

    public void buildWheels(ListView<String> wheels, ProgressBar wheelsProgressBar,
                            Button installButton, Label carWheelsLabel, Button removeButton){
        checkIfCarPresent();
        String selectedItem = wheels.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            partVerifier.verifyPartPresent(carContext.getCar().getBody(), Messages.BODY_WAS_NOT_INSTALLED);
            carProgressService.installWheels(wheelsProgressBar, selectedItem, carWheelsLabel, installButton, removeButton);
        } else {
            throw new PartIsMissingException(Messages.WHEELS_ARE_NOT_CHOSEN);
        }
    }

    public void removeWheels(ProgressBar wheelsProgress, Button installWheelsButton, Label carWheelsLabel, Button removeWheelsButton) {
        carProgressService.removeWheels(wheelsProgress, installWheelsButton, carWheelsLabel, removeWheelsButton);
    }

    public void finishCar(ProgressBar carProgress, Button finishCarButton) {
        checkIfCarPresent();
        partVerifier.verifyPartsPresent(carContext.getCar().getBody(), carContext.getCar().getEngine(), carContext.getCar().getWheels());
        carProgressService.buildCar(carProgress, finishCarButton);
    }

    private void checkIfCarPresent() {
        if (carContext.getCar() == null) {
            carContext.setCar(new Car());
        }
    }
}

package com.training.carfactory.model.service.impl.util;

import com.training.carfactory.controller.context.CarContext;
import com.training.carfactory.model.entity.Engine;
import com.training.carfactory.model.exception.IncorrectPartException;
import com.training.carfactory.model.exception.IncorrectResembleOrderException;
import com.training.carfactory.model.exception.PartIsMissingException;
import com.training.carfactory.model.service.CarService;
import com.training.carfactory.model.service.EngineService;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.concurrent.CountDownLatch;

public class CarProgressService {

    private ProgressBarSimulator progressBarSimulator;
    private CarPropertyUpdater carPropertyUpdater;
    private CarContext carContext;
    private EngineService engineService;

    public CarProgressService(ProgressBarSimulator progressBarSimulator, CarPropertyUpdater carPropertyUpdater,
                              CarContext carContext, EngineService engineService) {
        this.progressBarSimulator = progressBarSimulator;
        this.carPropertyUpdater = carPropertyUpdater;
        this.carContext = carContext;
        this.engineService = engineService;
    }

    public void installBody(ProgressBar bodyProgressBar, String selectedBody, Label carBodyLabel, Button installButton, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        installButton.setDisable(true);
        progressBarSimulator.simulateProgress(bodyProgressBar, CarProperties.BODY_ASSEMBLE_DELAY, cdl);
        carPropertyUpdater.updateAfterBodyInstall(selectedBody, removeButton, carBodyLabel, cdl);
    }

    public void removeBody(ProgressBar bodyProgressBar, Button installButton, Label carBodyLabel, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        removeButton.setDisable(true);
        progressBarSimulator.simulateDownTimeProgress(bodyProgressBar, CarProperties.BODY_RESEMBLE_DELAY, cdl);
        carPropertyUpdater.updateAfterBodyRemove(installButton, carBodyLabel, cdl);
    }

    public void installEngine(ProgressBar engineProgressBar, String selectedItem, Label carEngineLabel, Button installButton, Button removeButton){
        Engine engine = engineService.getByName(selectedItem);
        verifyOtherPartsStatus();
        verifyEngineAndBodyAreComparable(engine);
        CountDownLatch cdl = new CountDownLatch(1);
        installButton.setDisable(true);
        progressBarSimulator.simulateProgress(engineProgressBar, CarProperties.ENGINE_ASSEMBLE_DELAY, cdl);
        carPropertyUpdater.updateAfterEngineInstall(selectedItem, removeButton, carEngineLabel, cdl);
    }

      private void verifyEngineAndBodyAreComparable(Engine engine) {
        if(engine.getVolume() < carContext.getCar().getBody().getMinEngineVolume() ||
                engine.getVolume() > carContext.getCar().getBody().getMaxEngineVolume()){
            throw new IncorrectPartException(Messages.ENGINE_WAS_NOT_SUPPORTED);
        }
    }

    public void removeEngine(ProgressBar engineProgressBar,Button installButton, Label carEngineLabel, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        removeButton.setDisable(true);
        progressBarSimulator.simulateDownTimeProgress(engineProgressBar, CarProperties.ENGINE_RESEMBLE_DELAY, cdl);
        carPropertyUpdater.updateAfterEngineRemove(installButton, carEngineLabel, cdl);
    }

    public void installWheels(ProgressBar wheelsProgressBar, String selectedWheels, Label carWheelsLabel, Button installButton, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        installButton.setDisable(true);
        progressBarSimulator.simulateProgress(wheelsProgressBar, CarProperties.WHEELS_ASSEMBLE_DELAY, cdl);
        carPropertyUpdater.updateAfterWheelsInstall(selectedWheels, removeButton, carWheelsLabel, cdl);
    }

    public void removeWheels(ProgressBar wheelsProgressBar, Button installButton,  Label carWheelsLabel, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        removeButton.setDisable(true);
        progressBarSimulator.simulateDownTimeProgress(wheelsProgressBar, CarProperties.WHEELS_RESEMBLE_DELAY, cdl);
        carPropertyUpdater.updateAfterWheelsRemove(installButton, carWheelsLabel, cdl);
    }

    public void installSalon(ProgressBar salonProgressBar, String selectedSalon, Label carSalonLabel, Button installButton, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        installButton.setDisable(true);
        progressBarSimulator.simulateProgress(salonProgressBar, CarProperties.SALON_ASSEMBLE_DELAY, cdl);
        carPropertyUpdater.updateAfterSalonInstall(selectedSalon, removeButton, carSalonLabel, cdl);

    }

    public void removeSalon (ProgressBar salonProgressBar, Button installButton,  Label carSalonLabel, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        removeButton.setDisable(true);
        progressBarSimulator.simulateDownTimeProgress(salonProgressBar, CarProperties.SALON_RESEMBLE_DELAY, cdl);
        carPropertyUpdater.updateAfterSalonRemove(installButton, carSalonLabel, cdl);
    }

    public void verifyOtherPartsStatus(){
        if (ProgressBarSimulator.getStatus() == PartStatus.INSTALLING){
            throw new IncorrectResembleOrderException(Messages.PART_CANT_BE_INSTALLED_OR_REMOVED);
        } else if (ProgressBarSimulator.getStatus() == PartStatus.REMOVING){
            throw new PartIsMissingException(Messages.PART_CANT_BE_INSTALLED);
        }
    }

    public void buildCar(ProgressBar carProgress, Button finishCarButton) {
        CountDownLatch cdl = new CountDownLatch(1);
        finishCarButton.setDisable(true);
        progressBarSimulator.simulateProgress(carProgress, CarProperties.CAR_ASSEMBLE_DELAY, cdl);
        carPropertyUpdater.updateAfterCarInstall(cdl);
    }
}

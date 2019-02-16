package com.training.carfactory.model.service.impl.util;

import com.training.carfactory.model.exception.IncorrectResembleOrderException;
import com.training.carfactory.model.exception.PartIsMissingException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.concurrent.CountDownLatch;

public class CarProgressService {

    private ProgressBarSimulator progressBarSimulator;
    private CarPropertyUpdater carPropertyUpdater;

    public CarProgressService(ProgressBarSimulator progressBarSimulator, CarPropertyUpdater carPropertyUpdater) {
        this.progressBarSimulator = progressBarSimulator;
        this.carPropertyUpdater = carPropertyUpdater;
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

    public void installEngine(ProgressBar engineProgressBar, String selectedEngine, Label carEngineLabel, Button installButton, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        installButton.setDisable(true);
        progressBarSimulator.simulateProgress(engineProgressBar, CarProperties.ENGINE_ASSEMBLE_DELAY, cdl);
        carPropertyUpdater.updateAfterEngineInstall(selectedEngine, removeButton, carEngineLabel, cdl);
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

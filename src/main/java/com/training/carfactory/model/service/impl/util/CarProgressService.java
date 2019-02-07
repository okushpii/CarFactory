package com.training.carfactory.model.service.impl.util;

import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.exception.IncorrectResembleOrderException;
import com.training.carfactory.model.exception.PartIsMissingException;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import java.util.concurrent.CountDownLatch;

public class CarProgressService {

    private ProgressBarSimulator progressBarSimulator;
    private CarPropertyUpdater carPropertyUpdater;

    public CarProgressService(ProgressBarSimulator progressBarSimulator, CarPropertyUpdater carPropertyUpdater) {
        this.progressBarSimulator = progressBarSimulator;
        this.carPropertyUpdater = carPropertyUpdater;
    }

    public void installBody(ProgressBar bodyProgressBar, Car car, String selectedBody, Button installButton, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        installButton.setDisable(true);
        progressBarSimulator.simulateProgress(bodyProgressBar, 30, cdl);
        carPropertyUpdater.updateAfterBodyInstall(car, selectedBody, removeButton, cdl);
    }

    public void removeBody(ProgressBar bodyProgressBar, Car car, Button installButton, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        removeButton.setDisable(true);
        progressBarSimulator.simulateDownTimeProgress(bodyProgressBar, 20, cdl);
        carPropertyUpdater.updateAfterBodyRemove(car, installButton, cdl);
    }

    public void installEngine(ProgressBar engineProgressBar, Car car, String selectedEngine, Button installButton, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        installButton.setDisable(true);
        progressBarSimulator.simulateProgress(engineProgressBar, 50, cdl);
        carPropertyUpdater.updateAfterEngineInstall(car, selectedEngine, removeButton, cdl);
    }

    public void removeEngine(ProgressBar engineProgressBar, Car car, Button installButton, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        removeButton.setDisable(true);
        progressBarSimulator.simulateDownTimeProgress(engineProgressBar, 30, cdl);
        carPropertyUpdater.updateAfterEngineRemove(car, installButton, cdl);
    }

    public void installWheels(ProgressBar wheelsProgressBar, Car car, String selectedWheels, Button installButton, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        installButton.setDisable(true);
        progressBarSimulator.simulateProgress(wheelsProgressBar, 50, cdl);
        carPropertyUpdater.updateAfterWheelsInstall(car, selectedWheels, removeButton, cdl);
    }

    public void removeWheels(ProgressBar wheelsProgressBar, Car car, Button installButton, Button removeButton){
        verifyOtherPartsStatus();
        CountDownLatch cdl = new CountDownLatch(1);
        removeButton.setDisable(true);
        progressBarSimulator.simulateDownTimeProgress(wheelsProgressBar, 30, cdl);
        carPropertyUpdater.updateAfterWheelsRemove(car, installButton, cdl);
    }

    public void verifyOtherPartsStatus(){
        if (ProgressBarSimulator.getStatus() == PartStatus.INSTALLING){
            throw new IncorrectResembleOrderException("Part can`t be installed or removed now");
        } else if (ProgressBarSimulator.getStatus() == PartStatus.REMOVING){
            throw new PartIsMissingException("Part can`t be installed now");
        }
    }
}

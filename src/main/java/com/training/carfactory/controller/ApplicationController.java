package com.training.carfactory.controller;

import com.training.carfactory.controller.context.ApplicationContext;
import com.training.carfactory.controller.facade.ApplicationFacade;
import com.training.carfactory.controller.facade.CarFacade;
import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.exception.ConnectionFailedException;
import com.training.carfactory.model.exception.IncorrectResembleOrderException;
import com.training.carfactory.model.exception.PartIsMissingException;
import com.training.carfactory.model.service.impl.util.Messages;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.*;

public class ApplicationController {

    @FXML
    private AnchorPane menu;
    @FXML
    private AnchorPane bodyStep;
    @FXML
    private AnchorPane engineStep;
    @FXML
    private AnchorPane wheelsStep;
    @FXML
    private AnchorPane finalStep;
    @FXML
    private TableView<Car> carTableView;
    @FXML
    private TableColumn<Car, Long> carIdColumn;
    @FXML
    private TableColumn<Car, String> bodyColumn;
    @FXML
    private TableColumn<Car, String> engineColumn;
    @FXML
    private TableColumn<Car, String> wheelsColumn;

    @FXML
    private ListView<String> bodiesListView;
    @FXML
    private Pane bodyDetailsPane;
    @FXML
    private Label bodyNameLabel;
    @FXML
    private Label bodyTypeLabel;
    @FXML
    private Label bodyPriceLabel;
    @FXML
    private ImageView bodyImage;
    @FXML
    private Button installBodyButton;
    @FXML
    private Button removeBodyButton;
    @FXML
    private ProgressBar bodyProgress;

    @FXML
    private ListView<String> engineListView;
    @FXML
    private Pane engineDetailsPane;
    @FXML
    private Label engineNameLabel;
    @FXML
    private Label engineVolumeLabel;
    @FXML
    private Label enginePowerLabel;
    @FXML
    private Label enginePriceLabel;
    @FXML
    private ImageView engineImage;
    @FXML
    private Button installEngineButton;
    @FXML
    private Button removeEngineButton;
    @FXML
    private ProgressBar engineProgress;

    @FXML
    private ListView<String> wheelsListView;
    @FXML
    private Pane wheelsDetailsPane;
    @FXML
    private Label wheelsNameLabel;
    @FXML
    private Label wheelsSizeLabel;
    @FXML
    private Label wheelsPriceLabel;
    @FXML
    private ImageView wheelsImage;
    @FXML
    private Button installWheelsButton;
    @FXML
    private Button removeWheelsButton;
    @FXML
    private ProgressBar wheelsProgress;
    @FXML
    private Label bodyCarLabel;
    @FXML
    private Label engineCarLabel;
    @FXML
    private Label wheelsCarLabel;
    @FXML
    private ProgressBar carProgress;
    @FXML
    private Button finishCarButton;
    @FXML
    private Label exceptionLabel;

    private ApplicationFacade applicationFacade;
    private CarFacade carFacade;

    public void initialize() {
        ApplicationContext.getInstance().initController(this);
        initPage();
        toMenu();
    }

    public void toBodyStepPage() {
        try {
            switchToPage(bodyStep);
            applicationFacade.refreshBodyElements(bodyProgress, installBodyButton, removeBodyButton, bodiesListView);
        } catch (ConnectionFailedException ex) {
            exceptionLabel.setText(Messages.CONNECTION_PROBLEM);
        }
    }

    public void toEngineStepPage() {
        try {
            switchToPage(engineStep);
            applicationFacade.refreshEngineElements(engineProgress, installEngineButton, removeEngineButton, engineListView);
        } catch (ConnectionFailedException ex) {
            exceptionLabel.setText(Messages.CONNECTION_PROBLEM);
        }
    }

    public void toWheelsStepPage() {
        try {
            switchToPage(wheelsStep);
            applicationFacade.refreshWheelsElements(wheelsProgress, installWheelsButton, removeWheelsButton, wheelsListView);
        } catch (ConnectionFailedException ex) {
            exceptionLabel.setText(Messages.CONNECTION_PROBLEM);
        }
    }

    public void toFinalStepPage(){
        switchToPage(finalStep);
        applicationFacade.refreshCarElements(carProgress, bodyCarLabel, engineCarLabel, wheelsCarLabel, finishCarButton);
    }

    public void exit(){ System.exit(0); }

    public void toMenu() {
        try {
            switchToPage(menu);
            applicationFacade.initCarTable(carTableView, carIdColumn, bodyColumn, engineColumn, wheelsColumn);
        } catch (ConnectionFailedException ex) {
            exceptionLabel.setText(Messages.CONNECTION_PROBLEM);
        }
    }

    public void installBody() {
        try {
            carFacade.buildBody(bodiesListView, bodyProgress, installBodyButton, bodyCarLabel, removeBodyButton);
        } catch (PartIsMissingException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void removeBody() {
        try {
            carFacade.removeBody(bodyProgress, installBodyButton, bodyCarLabel, removeBodyButton);
        } catch (IncorrectResembleOrderException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void installEngine() {
        try {
            carFacade.buildEngine(engineListView, engineProgress, installEngineButton, engineCarLabel, removeEngineButton);
        } catch (PartIsMissingException | IncorrectResembleOrderException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void removeEngine() {
        try {
            carFacade.removeEngine(engineProgress, installEngineButton, engineCarLabel, removeEngineButton);
        } catch (IncorrectResembleOrderException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void installWheels() {
        try {
            carFacade.buildWheels(wheelsListView, wheelsProgress, installWheelsButton, wheelsCarLabel, removeWheelsButton);
        } catch (PartIsMissingException | IncorrectResembleOrderException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void removeWheels() {
        try {
            carFacade.removeWheels(wheelsProgress, installWheelsButton, wheelsCarLabel, removeWheelsButton);
        } catch (IncorrectResembleOrderException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void finishCar() {
        try {
            carFacade.finishCar(carProgress, finishCarButton);
            removeBodyButton.setDisable(true);
            removeEngineButton.setDisable(true);
            removeWheelsButton.setDisable(true);
        } catch (PartIsMissingException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void selectBody() {
        exceptionLabel.setText("");
        applicationFacade.chooseBody(bodyDetailsPane, bodyNameLabel,
                bodyTypeLabel, bodyPriceLabel, bodyImage, bodiesListView);
    }

    public void selectEngine() {
        applicationFacade.chooseEngine(engineDetailsPane, engineNameLabel,
                engineVolumeLabel, enginePowerLabel, enginePriceLabel, engineImage, engineListView);
    }

    public void selectWheels() {
        applicationFacade.chooseWheels(wheelsDetailsPane, wheelsNameLabel, wheelsSizeLabel, wheelsPriceLabel, wheelsImage, wheelsListView);
    }

    private void switchToPage(AnchorPane node) {
        exceptionLabel.setText("");
        applicationFacade.toPage(node);
    }

    private void initPage() {
        try {
            applicationFacade.initPage(menu);
        } catch (ConnectionFailedException ex) {
            exceptionLabel.setText(Messages.CONNECTION_PROBLEM);
        }
    }

    public void setApplicationFacade(ApplicationFacade applicationFacade) {
        this.applicationFacade = applicationFacade;
    }

    public void setCarFacade(CarFacade carFacade) {
        this.carFacade = carFacade;
    }
}

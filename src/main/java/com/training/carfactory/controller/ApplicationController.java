package com.training.carfactory.controller;

import com.training.carfactory.controller.facade.ApplicationFacade;
import com.training.carfactory.controller.facade.CarFacade;
import com.training.carfactory.model.exception.ConnectionFailedException;
import com.training.carfactory.model.exception.PartIsMissingException;
import com.training.carfactory.model.service.context.ApplicationContext;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    private ComboBox<String> bodiesList;
    @FXML
    private ComboBox<String> enginesList;
    @FXML
    private ComboBox<String> wheelsList;
    @FXML
    private Pane bodyDetailsPane;
    @FXML
    private Label bodyNameLabel;
    @FXML
    private Label bodyTypeLabel;
    @FXML
    private Label bodyPriceLabel;
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
    private Pane wheelsDetailsPane;
    @FXML
    private Label wheelsNameLabel;
    @FXML
    private Label wheelsSizeLabel;
    @FXML
    private Label wheelsPriceLabel;
    @FXML
    private Label exceptionLabel;

    private ApplicationFacade applicationFacade;
    private CarFacade carFacade;

    public void initialize(){
        ApplicationContext.getInstance().initController(this);
        initElements();
    }

    public void toBodyStepPage(){
        switchToPage(bodyStep);
    }

    public void toEngineStepPage(){
        carFacade.buildBody(bodiesList);
        switchToPage(engineStep);
    }

    public void toWheelsStepPage(){
        carFacade.buildEngine(enginesList);
        switchToPage(wheelsStep);
    }

    public void toMenu() {
        switchToPage(menu);
    }

    public void finishCar(){
        carFacade.buildWheels(wheelsList);
        try {
            carFacade.finishCar();
            switchToPage(menu);
        } catch (PartIsMissingException ex){
            exceptionLabel.setText("Some parts are missing");
        }
    }

    public void chooseBody(){
        applicationFacade.chooseBody(bodyDetailsPane, bodyNameLabel,
                bodyTypeLabel, bodyPriceLabel, bodiesList);
    }
    public void chooseEngine(){
        applicationFacade.chooseEngine(engineDetailsPane, engineNameLabel,
                engineVolumeLabel, enginePowerLabel, enginePriceLabel, enginesList);
    }
    public void chooseWheels(){
        applicationFacade.chooseWheels(wheelsDetailsPane, wheelsNameLabel, wheelsSizeLabel, wheelsPriceLabel, wheelsList);
    }

    private void switchToPage(AnchorPane node){
        exceptionLabel.setText("");
        applicationFacade.toPage(node);
    }

    private void initElements() {
        try {
            applicationFacade.init(menu, bodiesList, enginesList, wheelsList);
        } catch (ConnectionFailedException ex){
            exceptionLabel.setText("Some problems with connection");
        }
    }

    public void setApplicationFacade(ApplicationFacade applicationFacade) {
        this.applicationFacade = applicationFacade;
    }

    public void setCarFacade(CarFacade carFacade) {
        this.carFacade = carFacade;
    }
}

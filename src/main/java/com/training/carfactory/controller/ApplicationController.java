package com.training.carfactory.controller;

import com.training.carfactory.controller.facade.ApplicationFacade;
import com.training.carfactory.model.entity.Body;
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


    private ApplicationFacade applicationFacade;

    public void initialize(){
        ApplicationContext.getInstance().initController(this);
        applicationFacade.init(menu, bodiesList, enginesList, wheelsList);
    }

    public void toBodyStepPage(){
        applicationFacade.toBodyStage(bodyStep);
    }

    public void toEngineStepPage(){
        applicationFacade.toEngineStage(engineStep);
    }

    public void toWheelsStepPage(){
        applicationFacade.toWheelsStage(wheelsStep);
    }

    public void toMenu() {
        applicationFacade.toMenu(menu);
    }

    public void finishCar(){
        applicationFacade.finishCar(menu);
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

    public void setApplicationFacade(ApplicationFacade applicationFacade) {
        this.applicationFacade = applicationFacade;
    }
}

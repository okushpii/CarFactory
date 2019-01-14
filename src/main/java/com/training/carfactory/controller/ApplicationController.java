package com.training.carfactory.controller;

import com.training.carfactory.controller.facade.ApplicationFacade;
import com.training.carfactory.model.service.context.ApplicationContext;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

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

    private ApplicationFacade applicationFacade;

    public void initialize(){
        ApplicationContext.getInstance().initController(this);
        applicationFacade.init(menu, bodiesList);
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

    public void setApplicationFacade(ApplicationFacade applicationFacade) {
        this.applicationFacade = applicationFacade;
    }
}

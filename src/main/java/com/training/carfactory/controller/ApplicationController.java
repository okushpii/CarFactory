package com.training.carfactory.controller;

import com.training.carfactory.model.service.PageService;
import com.training.carfactory.model.service.impl.DefaultPageService;
import javafx.fxml.FXML;
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

    private PageService pageService = new DefaultPageService();

    @FXML
    public void initialize(){
        pageService.initializePages(menu, bodyStep, engineStep, wheelsStep);
    }

    @FXML
    public void toBodyStepPage(){
        switchToNextPage();
    }

    @FXML
    public void toEngineStepPage(){
       switchToNextPage();
    }

    @FXML
    public void toWheelsStepPage(){
       switchToNextPage();
    }

    @FXML
    public void toPreviousPage(){
        switchToPreviousPage();
    }

    public void finishCar(){
        switchToNextPage();
    }

    private void switchToNextPage(){
        pageService.getCurrentPage().setVisible(false);
        pageService.getNextPage().setVisible(true);
    }

    private void switchToPreviousPage(){
        pageService.getCurrentPage().setVisible(false);
        pageService.getPreviousPage().setVisible(true);
    }

}

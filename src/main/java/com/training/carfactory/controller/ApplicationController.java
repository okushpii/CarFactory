package com.training.carfactory.controller;

import com.training.carfactory.model.service.BodyService;
import com.training.carfactory.model.service.EngineService;
import com.training.carfactory.model.service.PageService;
import com.training.carfactory.model.service.context.ApplicationContext;
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

    private PageService pageService;
    private BodyService bodyService;
    private EngineService engineService;

    public void initialize(){
        ApplicationContext.getInstance().initController(this);
        pageService.setCurrentPage(menu);
    }

    public void toBodyStepPage(){
        pageService.switchToPage(bodyStep);
    }

    public void toEngineStepPage(){
        pageService.switchToPage(engineStep);
    }

    public void toWheelsStepPage(){
        pageService.switchToPage(wheelsStep);
    }

    public void toMenu() {
        pageService.switchToPage(menu);
    }

    public void finishCar(){
        pageService.switchToPage(menu);
    }

    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }

    public void setBodyService(BodyService bodyService) {
        this.bodyService = bodyService;
    }

    public void setEngineService(EngineService engineService){this.engineService = engineService;}
}

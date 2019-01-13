package com.training.carfactory.controller;

import com.training.carfactory.model.service.PageService;
import com.training.carfactory.model.service.impl.DefaultPageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    public void initialize(){
        pageService.setCurrentPage(menu);
    }

    public void toBodyStepPage(){
        switchPages(pageService.getCurrentPage(), bodyStep);
    }

    public void toEngineStepPage(){
        switchPages(pageService.getCurrentPage(), engineStep);
    }

    public void toWheelsStepPage(){
        switchPages(pageService.getCurrentPage(), wheelsStep);
    }

    public void toMenu() {
        switchPages(pageService.getCurrentPage(), menu);
    }

    public void finishCar(){
        switchPages(pageService.getCurrentPage(), menu);
    }

    private void switchPages(Node source, Node target){
        source.setVisible(false);
        target.setVisible(true);
        pageService.setCurrentPage(target);
    }
}

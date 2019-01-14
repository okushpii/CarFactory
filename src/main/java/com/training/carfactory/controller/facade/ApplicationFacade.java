package com.training.carfactory.controller.facade;

import com.training.carfactory.model.service.ElementService;
import com.training.carfactory.model.service.PageService;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public class ApplicationFacade {

    private PageService pageService;
    private ElementService elementService;

    public ApplicationFacade(PageService pageService, ElementService elementService) {
        this.pageService = pageService;
        this.elementService = elementService;
    }

    public void init(Node initialPage, ComboBox<String> bodiesList){
        pageService.setCurrentPage(initialPage);
        elementService.initElements(bodiesList);
    }

    public void toMenu(Node nextNode){
        pageService.switchToPage(nextNode);
    }

    public void toBodyStage(Node nextNode){
        pageService.switchToPage(nextNode);
    }

    public void toEngineStage(Node nextNode){
        pageService.switchToPage(nextNode);
    }

    public void toWheelsStage(Node nextNode){
        pageService.switchToPage(nextNode);
    }

    public void finishCar(Node nextNode){
        pageService.switchToPage(nextNode);
    }
}

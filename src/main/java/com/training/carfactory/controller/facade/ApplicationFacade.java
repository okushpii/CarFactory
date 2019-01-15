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

    public void init(Node initialPage, ComboBox<String> bodiesList, ComboBox<String> enginesList, ComboBox<String> wheelsList){
        pageService.setCurrentPage(initialPage);
        elementService.initBodyElements(bodiesList);
        elementService.initEngineElements(enginesList);
        elementService.initWheelsElements(wheelsList);
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

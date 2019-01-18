package com.training.carfactory.controller.facade;

import com.training.carfactory.model.entity.Body;
import com.training.carfactory.model.service.BodyService;
import com.training.carfactory.model.service.ElementService;
import com.training.carfactory.model.service.PageService;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.math.BigDecimal;
import java.util.Optional;

public class ApplicationFacade {

    private PageService pageService;
    private ElementService elementService;
    private BodyService bodyService;

    public ApplicationFacade(PageService pageService, ElementService elementService, BodyService bodyService) {
        this.pageService = pageService;
        this.elementService = elementService;
        this.bodyService = bodyService;
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

    public void chooseBody(Pane bodyDetailsPane, Label bodyNameLabel,
                           Label bodyTypeLabel, Label bodyPriceLabel, ComboBox<String> bodiesList){
        bodyDetailsPane.setVisible(true);
        Body body = Optional.of(bodyService.getByName(bodiesList.getValue())).get();
        bodyNameLabel.setText(body.getName());
        bodyTypeLabel.setText(body.getType().toString());
        bodyPriceLabel.setText(new BigDecimal(body.getPrice()).toString());
    }
}

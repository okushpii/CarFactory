package com.training.carfactory.controller.facade;

import com.training.carfactory.model.entity.Body;
import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.entity.Engine;
import com.training.carfactory.model.entity.Wheels;
import com.training.carfactory.model.service.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.math.BigDecimal;
import java.util.Optional;

public class ApplicationFacade {

    private PageService pageService;
    private ElementService elementService;
    private BodyService bodyService;
    private EngineService engineService;
    private WheelsService wheelsService;

    public ApplicationFacade(PageService pageService, ElementService elementService, BodyService bodyService, EngineService engineService,
                             WheelsService wheelsService) {
        this.pageService = pageService;
        this.elementService = elementService;
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.wheelsService = wheelsService;
    }
    public void initPartComboBoxes(Node initialPage, ListView<String> bodiesListView, ComboBox<String> enginesList, ComboBox<String> wheelsList, TableView<Car> carTableView){
        pageService.setCurrentPage(initialPage);
        elementService.initBodyElements(bodiesListView);
        elementService.initEngineElements(enginesList);
        elementService.initWheelsElements(wheelsList);
    }

    public void initCarTable(TableView<Car> carTableView, TableColumn<Car, Long> carIdColumn,
                             TableColumn<Car, String> bodyColumn, TableColumn<Car,
            String> engineColumn, TableColumn<Car, String> wheelsColumn){

        elementService.initCarTableElements(carTableView, carIdColumn, bodyColumn, engineColumn, wheelsColumn);
    }

    public void toPage(Node nextNode){
        pageService.switchToPage(nextNode);
    }

    public void chooseBody(Pane bodyDetailsPane, Label bodyNameLabel,
                           Label bodyTypeLabel, Label bodyPriceLabel, ListView<String> bodiesListView){
        bodyDetailsPane.setVisible(true);
        Body body = Optional.of(bodyService.getByName(bodiesListView.getSelectionModel().getSelectedItem())).get();
        bodyNameLabel.setText(body.getName());
        bodyTypeLabel.setText(body.getType().toString());
        bodyPriceLabel.setText(new BigDecimal(body.getPrice()).toString());
    }

    public void chooseEngine(Pane engineDetailsPane, Label engineNameLabel, Label engineVolumeLabel,
                             Label enginePowerLabel, Label enginePriceLabel, ComboBox<String> enginesList){
        engineDetailsPane.setVisible(true);
        Engine engine = Optional.of(engineService.getByName(enginesList.getValue())).get();
        engineNameLabel.setText(engine.getName());
        engineVolumeLabel.setText(engine.getVolume().toString());
        enginePowerLabel.setText(engine.getPower().toString());
        enginePriceLabel.setText(new BigDecimal(engine.getPrice()).toString());
    }
    public void chooseWheels(Pane wheelsDetailsPane, Label wheelsNameLabel,
                           Label wheelsSizeLabel, Label wheelsPriceLabel, ComboBox<String> wheelsList){
        wheelsDetailsPane.setVisible(true);
        Wheels wheels = Optional.of(wheelsService.getByName(wheelsList.getValue())).get();
        wheelsNameLabel.setText(wheels.getName());
        wheelsSizeLabel.setText(wheels.getSize().toString());
        wheelsPriceLabel.setText(new BigDecimal(wheels.getPrice()).toString());
    }
}

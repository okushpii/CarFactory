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
    public void initPartComboBoxes(Node initialPage, ListView<String> bodiesListView, ListView<String> enginesList, ListView<String> wheelsList, TableView<Car> carTableView){
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
        String selectedItem = bodiesListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            bodyDetailsPane.setVisible(true);
            Body body = bodyService.getByName(selectedItem);
            bodyNameLabel.setText(body.getName());
            bodyTypeLabel.setText(body.getType().toString());
            bodyPriceLabel.setText(new BigDecimal(body.getPrice()).toString());
        }
    }

    public void chooseEngine(Pane engineDetailsPane, Label engineNameLabel, Label engineVolumeLabel,
                             Label enginePowerLabel, Label enginePriceLabel, ListView<String> enginesList){
        String selectedItem = enginesList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            engineDetailsPane.setVisible(true);
            Engine engine = engineService.getByName(selectedItem);
            engineNameLabel.setText(engine.getName());
            engineVolumeLabel.setText(engine.getVolume().toString());
            enginePowerLabel.setText(engine.getPower().toString());
            enginePriceLabel.setText(new BigDecimal(engine.getPrice()).toString());
        }
    }
    public void chooseWheels(Pane wheelsDetailsPane, Label wheelsNameLabel,
                           Label wheelsSizeLabel, Label wheelsPriceLabel, ListView<String> wheelsList){
        String selectedItem = wheelsList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            wheelsDetailsPane.setVisible(true);
            Wheels wheels = wheelsService.getByName(selectedItem);
            wheelsNameLabel.setText(wheels.getName());
            wheelsSizeLabel.setText(wheels.getSize().toString());
            wheelsPriceLabel.setText(new BigDecimal(wheels.getPrice()).toString());
        }
    }
}

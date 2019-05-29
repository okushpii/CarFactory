package com.training.carfactory.controller.facade;

import com.training.carfactory.controller.context.CarContext;
import com.training.carfactory.model.entity.Body;
import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.entity.Engine;
import com.training.carfactory.model.entity.Wheels;
import com.training.carfactory.model.service.*;
import com.training.carfactory.model.service.impl.util.Messages;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import java.math.BigDecimal;
import java.util.Optional;

public class ApplicationFacade {

    private PageService pageService;
    private ElementService elementService;
    private BodyService bodyService;
    private EngineService engineService;
    private WheelsService wheelsService;
    private CarContext carContext;

    public ApplicationFacade(PageService pageService, ElementService elementService, BodyService bodyService, EngineService engineService,
                             WheelsService wheelsService, CarContext carContext) {
        this.pageService = pageService;
        this.elementService = elementService;
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.wheelsService = wheelsService;
        this.carContext = carContext;
    }
    public void initPage(Node initialPage){
        pageService.setCurrentPage(initialPage);
    }

    public void initCarTable(TableView<Car> carTableView, TableColumn<Car, Long> carIdColumn,
                             TableColumn<Car, String> bodyColumn, TableColumn<Car,
            String> engineColumn, TableColumn<Car, String> wheelsColumn){

        elementService.initCarTableElements(carTableView, carIdColumn, bodyColumn, engineColumn, wheelsColumn);
    }

    public void refreshBodyElements(ProgressBar bodyProgress, Button installBodyButton, Button removeBodyButton, ListView<String> bodiesListView){
        elementService.initBodyElements(bodiesListView);
        if (carContext.getCar() == null) {
            bodyProgress.setProgress(0);
            installBodyButton.setDisable(false);
            removeBodyButton.setDisable(true);
        }
    }

    public void refreshEngineElements(ProgressBar engineProgress, Button installEngineButton, Button removeEngineButton, ListView<String> engineListView) {
        elementService.initEngineElements(engineListView);
        if (carContext.getCar() == null) {
            engineProgress.setProgress(0);
            installEngineButton.setDisable(false);
            removeEngineButton.setDisable(true);
        }
    }

    public void refreshWheelsElements(ProgressBar wheelsProgress, Button installWheelsButton, Button removeWheelsButton, ListView<String> wheelsListView) {
        elementService.initWheelsElements(wheelsListView);
        if (carContext.getCar() == null) {
            wheelsProgress.setProgress(0);
            installWheelsButton.setDisable(false);
            removeWheelsButton.setDisable(true);
        }
    }

    public void refreshCarElements(ProgressBar carProgress, Label bodyCarLabel, Label engineCarLabel,
                                   Label wheelsCarLabel, Button finishCarButton) {
        if (carContext.getCar() == null) {
            carProgress.setProgress(0);
            bodyCarLabel.setText(Messages.NOT_INSTALLED);
            engineCarLabel.setText(Messages.NOT_INSTALLED);
            wheelsCarLabel.setText(Messages.NOT_INSTALLED);
            finishCarButton.setDisable(false);
        }
    }

    public void toPage(Node nextNode){
        pageService.switchToPage(nextNode);
    }

    public void chooseBody(Pane bodyDetailsPane, Label bodyNameLabel,
                           Label bodyTypeLabel, Label bodyPriceLabel, ImageView bodyImage, ListView<String> bodiesListView){
        String selectedItem = bodiesListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            bodyDetailsPane.setVisible(true);
            Body body = bodyService.getByName(selectedItem);
            bodyNameLabel.setText(body.getName());
            bodyTypeLabel.setText(body.getType().toString());
            bodyPriceLabel.setText(new BigDecimal(body.getPrice()).toString());
            bodyImage.setImage(new Image(getClass().getResourceAsStream(body.getImageUrl())));
        }
    }

    public void chooseEngine(Pane engineDetailsPane, Label engineNameLabel, Label engineVolumeLabel,
                             Label enginePowerLabel, Label enginePriceLabel, ImageView engineImage, ListView<String> enginesList){
        String selectedItem = enginesList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            engineDetailsPane.setVisible(true);
            Engine engine = engineService.getByName(selectedItem);
            engineNameLabel.setText(engine.getName());
            engineVolumeLabel.setText(engine.getVolume().toString());
            enginePowerLabel.setText(engine.getPower().toString());
            enginePriceLabel.setText(new BigDecimal(engine.getPrice()).toString());
            engineImage.setImage(new Image(getClass().getResourceAsStream(engine.getImageUrl())));
        }
    }
    public void chooseWheels(Pane wheelsDetailsPane, Label wheelsNameLabel,
                           Label wheelsSizeLabel, Label wheelsPriceLabel, ImageView wheelsImage, ListView<String> wheelsList){
        String selectedItem = wheelsList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            wheelsDetailsPane.setVisible(true);
            Wheels wheels = wheelsService.getByName(selectedItem);
            wheelsNameLabel.setText(wheels.getName());
            wheelsSizeLabel.setText(wheels.getSize().toString());
            wheelsPriceLabel.setText(new BigDecimal(wheels.getPrice()).toString());
            wheelsImage.setImage(new Image(getClass().getResourceAsStream(wheels.getImageUrl())));
        }
    }
}

package com.training.carfactory.controller;

import com.training.carfactory.controller.facade.ApplicationFacade;
import com.training.carfactory.controller.facade.CarFacade;
import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.exception.ConnectionFailedException;
import com.training.carfactory.model.exception.PartIsMissingException;
import com.training.carfactory.model.service.context.ApplicationContext;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    private ListView<String> bodiesListView;
    @FXML
    private ComboBox<String> enginesList;
    @FXML
    private ComboBox<String> wheelsList;
    @FXML
    private TableView<Car> carTableView;
    @FXML
    private TableColumn<Car, Long> carIdColumn;
    @FXML
    private TableColumn<Car, String> bodyColumn;
    @FXML
    private TableColumn<Car, String> engineColumn;
    @FXML
    private TableColumn<Car, String> wheelsColumn;
    @FXML
    private Pane bodyDetailsPane;
    @FXML
    private Label bodyNameLabel;
    @FXML
    private Label bodyTypeLabel;
    @FXML
    private Label bodyPriceLabel;
    @FXML
    private Button installBodyButton;
    @FXML
    private Button removeBodyButton;
    @FXML
    private ProgressBar bodyProgress;
    @FXML
    private Pane engineDetailsPane;
    @FXML
    private Label engineNameLabel;
    @FXML
    private Label engineVolumeLabel;
    @FXML
    private Label enginePowerLabel;
    @FXML
    private Label enginePriceLabel;
    @FXML
    private Pane wheelsDetailsPane;
    @FXML
    private Label wheelsNameLabel;
    @FXML
    private Label wheelsSizeLabel;
    @FXML
    private Label wheelsPriceLabel;
    @FXML
    private Label exceptionLabel;

    private ApplicationFacade applicationFacade;
    private CarFacade carFacade;

    public void initialize(){
        ApplicationContext.getInstance().initController(this);
        initElements();
    }

    public void toBodyStepPage(){
        switchToPage(bodyStep);
    }

    public void installBody(){
        try {
            carFacade.buildBody(bodiesListView, bodyProgress, installBodyButton, removeBodyButton);
        } catch (PartIsMissingException ex){
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void removeBody(){
        carFacade.removeBody(bodyProgress, installBodyButton, removeBodyButton);
    }

    public void toEngineStepPage(){
        switchToPage(engineStep);
    }

    public void toWheelsStepPage(){
        carFacade.buildEngine(enginesList);
        switchToPage(wheelsStep);
    }

    public void toMenu() {
        switchToPage(menu);
    }

    public void finishCar(){
        carFacade.buildWheels(wheelsList);
        try {
            carFacade.finishCar();
            switchToPage(menu);
            applicationFacade.initCarTable(carTableView, carIdColumn, bodyColumn, engineColumn, wheelsColumn);
        } catch (PartIsMissingException ex){
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void selectBody(){
        exceptionLabel.setText("");
        applicationFacade.chooseBody(bodyDetailsPane, bodyNameLabel,
                bodyTypeLabel, bodyPriceLabel, bodiesListView);
    }
    public void chooseEngine(){
        applicationFacade.chooseEngine(engineDetailsPane, engineNameLabel,
                engineVolumeLabel, enginePowerLabel, enginePriceLabel, enginesList);
    }
    public void chooseWheels(){
        applicationFacade.chooseWheels(wheelsDetailsPane, wheelsNameLabel, wheelsSizeLabel, wheelsPriceLabel, wheelsList);
    }

    private void switchToPage(AnchorPane node){
        exceptionLabel.setText("");
        applicationFacade.toPage(node);
    }

    private void initElements() {
        try {
            applicationFacade.initPartComboBoxes(menu, bodiesListView, enginesList, wheelsList, carTableView);
            applicationFacade.initCarTable(carTableView, carIdColumn, bodyColumn, engineColumn, wheelsColumn);
        } catch (ConnectionFailedException ex){
            exceptionLabel.setText("Some problems with connection");
        }
    }

    public void setApplicationFacade(ApplicationFacade applicationFacade) {
        this.applicationFacade = applicationFacade;
    }

    public void setCarFacade(CarFacade carFacade) {
        this.carFacade = carFacade;
    }
}

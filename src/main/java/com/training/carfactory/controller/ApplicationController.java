package com.training.carfactory.controller;

import com.training.carfactory.controller.facade.ApplicationFacade;
import com.training.carfactory.controller.facade.CarFacade;
import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.exception.ConnectionFailedException;
import com.training.carfactory.model.exception.IncorrectResembleOrderException;
import com.training.carfactory.model.exception.PartIsMissingException;
import com.training.carfactory.controller.context.ApplicationContext;
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
    private ListView<String> bodiesListView;
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
    private ListView<String> engineListView;
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
    private Button installEngineButton;
    @FXML
    private Button removeEngineButton;
    @FXML
    private ProgressBar engineProgress;

    @FXML
    private ListView<String> wheelsListView;
    @FXML
    private Pane wheelsDetailsPane;
    @FXML
    private Label wheelsNameLabel;
    @FXML
    private Label wheelsSizeLabel;
    @FXML
    private Label wheelsPriceLabel;
    @FXML
    private Button installWheelsButton;
    @FXML
    private Button removeWheelsButton;
    @FXML
    private ProgressBar wheelsProgress;

    @FXML
    private Label exceptionLabel;

    private ApplicationFacade applicationFacade;
    private CarFacade carFacade;

    public void initialize() {
        ApplicationContext.getInstance().initController(this);
        initElements();
    }

    public void toBodyStepPage() {
        switchToPage(bodyStep);
    }

    public void installBody() {
        try {
            carFacade.buildBody(bodiesListView, bodyProgress, installBodyButton, removeBodyButton);
        } catch (PartIsMissingException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void removeBody() {
        try {
            carFacade.removeBody(bodyProgress, installBodyButton, removeBodyButton);
        } catch (IncorrectResembleOrderException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void installEngine() {
        try {
            carFacade.buildEngine(engineListView, engineProgress, installEngineButton, removeEngineButton);
        } catch (PartIsMissingException | IncorrectResembleOrderException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void removeEngine() {
        try {
            carFacade.removeEngine(engineProgress, installEngineButton, removeEngineButton);
        } catch (IncorrectResembleOrderException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void toEngineStepPage() {
        switchToPage(engineStep);
    }

    public void installWheels() {
        try {
            carFacade.buildWheels(wheelsListView, wheelsProgress, installWheelsButton, removeWheelsButton);
        } catch (PartIsMissingException | IncorrectResembleOrderException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void removeWheels() {
        try {
            carFacade.removeWheels(wheelsProgress, installWheelsButton, removeWheelsButton);
        } catch (IncorrectResembleOrderException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void toWheelsStepPage() {
        switchToPage(wheelsStep);
    }

    public void toMenu() {
        switchToPage(menu);
    }

    public void finishCar() {
        try {
            carFacade.finishCar();
            switchToPage(menu);
            applicationFacade.initCarTable(carTableView, carIdColumn, bodyColumn, engineColumn, wheelsColumn);
        } catch (PartIsMissingException ex) {
            exceptionLabel.setText(ex.getMessage());
        }
    }

    public void selectBody() {
        exceptionLabel.setText("");
        applicationFacade.chooseBody(bodyDetailsPane, bodyNameLabel,
                bodyTypeLabel, bodyPriceLabel, bodiesListView);
    }

    public void selectEngine() {
        applicationFacade.chooseEngine(engineDetailsPane, engineNameLabel,
                engineVolumeLabel, enginePowerLabel, enginePriceLabel, engineListView);
    }

    public void selectWheels() {
        applicationFacade.chooseWheels(wheelsDetailsPane, wheelsNameLabel, wheelsSizeLabel, wheelsPriceLabel, wheelsListView);
    }

    private void switchToPage(AnchorPane node) {
        exceptionLabel.setText("");
        applicationFacade.toPage(node);
    }

    private void initElements() {
        try {
            applicationFacade.initPartComboBoxes(menu, bodiesListView, engineListView, wheelsListView, carTableView);
            applicationFacade.initCarTable(carTableView, carIdColumn, bodyColumn, engineColumn, wheelsColumn);
        } catch (ConnectionFailedException ex) {
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

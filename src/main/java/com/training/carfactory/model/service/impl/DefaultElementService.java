package com.training.carfactory.model.service.impl;

import com.sun.javafx.collections.ObservableListWrapper;
import com.training.carfactory.model.entity.Body;
import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.entity.Engine;
import com.training.carfactory.model.entity.Wheels;
import com.training.carfactory.model.service.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.Collectors;

public class DefaultElementService implements ElementService {

    private BodyService bodyService;
    private EngineService engineService;
    private WheelsService wheelsService;
    private CarService carService;

    public DefaultElementService(BodyService bodyService, EngineService engineService, WheelsService wheelsService, CarService carService) {
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.wheelsService = wheelsService;
        this.carService = carService;
    }

    @Override
    public void initBodyElements(ComboBox<String> bodiesList) {
        initBodies(bodiesList);
    }

    @Override
    public void initEngineElements(ComboBox<String> enginesList) {
        initEngines(enginesList);
    }

    @Override
    public void initWheelsElements(ComboBox<String> wheelsList) {
        initWheelsList(wheelsList);
    }

    @Override
    public void initCarTableElements(TableView<Car> carTableView, TableColumn<Car, Long> carIdColumn,
                                     TableColumn<Car, String> bodyColumn, TableColumn<Car,
            String> engineColumn, TableColumn<Car, String> wheelsColumn) {

        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bodyColumn.setCellValueFactory(car -> new SimpleStringProperty(car.getValue().getBody().getName()));
        engineColumn.setCellValueFactory(car -> new SimpleStringProperty(car.getValue().getEngine().getName()));
        wheelsColumn.setCellValueFactory(car -> new SimpleStringProperty(car.getValue().getWheels().getName()));

        carTableView.setItems(new ObservableListWrapper<>(carService.getAll()));
    }

    private void initBodies(ComboBox<String> bodiesList){
        bodiesList.setItems(new ObservableListWrapper<>(bodyService.getAll().
                stream().map(Body::getName).collect(Collectors.toList())));
    }

    private void initEngines(ComboBox<String> enginesList){
        enginesList.setItems(new ObservableListWrapper<>(engineService.getAll().
                stream().map(Engine::getName).collect(Collectors.toList())));
    }

    private void initWheelsList(ComboBox<String> wheelsList){
        wheelsList.setItems(new ObservableListWrapper<>(wheelsService.getAll().
                stream().map(Wheels::getName).collect(Collectors.toList())));
    }
}

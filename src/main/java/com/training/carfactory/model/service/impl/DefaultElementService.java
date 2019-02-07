package com.training.carfactory.model.service.impl;

import com.sun.javafx.collections.ObservableListWrapper;
import com.training.carfactory.model.entity.Body;
import com.training.carfactory.model.entity.Car;
import com.training.carfactory.model.entity.Engine;
import com.training.carfactory.model.entity.Wheels;
import com.training.carfactory.model.service.*;
import com.training.carfactory.model.service.impl.util.ValueFormatterService;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.Collectors;

public class DefaultElementService implements ElementService {

    private BodyService bodyService;
    private EngineService engineService;
    private WheelsService wheelsService;
    private CarService carService;

    private ValueFormatterService valueFormatterService;

    public DefaultElementService(BodyService bodyService, EngineService engineService,
                                 WheelsService wheelsService, CarService carService, ValueFormatterService valueFormatterService) {
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.wheelsService = wheelsService;
        this.carService = carService;
        this.valueFormatterService = valueFormatterService;
    }

    @Override
    public void initBodyElements(ListView<String> bodiesListView) {
        initBodies(bodiesListView);
    }

    @Override
    public void initEngineElements(ListView<String> enginesList) {
        initEngines(enginesList);
    }

    @Override
    public void initWheelsElements(ListView<String> wheelsList) {
        initWheels(wheelsList);
    }

    @Override
    public void initCarTableElements(TableView<Car> carTableView, TableColumn<Car, Long> carIdColumn,
                                     TableColumn<Car, String> bodyColumn, TableColumn<Car,
            String> engineColumn, TableColumn<Car, String> wheelsColumn) {

        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bodyColumn.setCellValueFactory(car -> new SimpleStringProperty(valueFormatterService.formatBody(car.getValue().getBody())));
        engineColumn.setCellValueFactory(car -> new SimpleStringProperty(valueFormatterService.formatEngine(car.getValue().getEngine())));
        wheelsColumn.setCellValueFactory(car -> new SimpleStringProperty(valueFormatterService.formatWheels(car.getValue().getWheels())));

        carTableView.setItems(new ObservableListWrapper<>(carService.getAll()));
    }

    private void initBodies(ListView<String> bodiesListView){
        bodiesListView.setItems(new ObservableListWrapper<>(bodyService.getAll().
                stream().map(Body::getName).collect(Collectors.toList())));
    }

    private void initEngines(ListView<String> enginesList){
        enginesList.setItems(new ObservableListWrapper<>(engineService.getAll().
                stream().map(Engine::getName).collect(Collectors.toList())));
    }

    private void initWheels(ListView<String> wheelsList){
        wheelsList.setItems(new ObservableListWrapper<>(wheelsService.getAll().
                stream().map(Wheels::getName).collect(Collectors.toList())));
    }
}

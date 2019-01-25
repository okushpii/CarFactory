package com.training.carfactory.model.service;

import com.training.carfactory.model.entity.Car;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public interface ElementService {

    void initBodyElements(ListView<String> bodiesListView);

    void initEngineElements(ComboBox<String> enginesList);

    void initWheelsElements(ComboBox<String> wheelsList);

    void initCarTableElements(TableView<Car> carTableView, TableColumn<Car, Long> carIdColumn,
                              TableColumn<Car, String> bodyColumn, TableColumn<Car,
            String> engineColumn, TableColumn<Car, String> wheelsColumn);
}

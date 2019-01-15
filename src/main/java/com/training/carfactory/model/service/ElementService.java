package com.training.carfactory.model.service;

import javafx.scene.control.ComboBox;

public interface ElementService {

    void initBodyElements(ComboBox<String> bodiesList);
    void initEngineElements(ComboBox<String> enginesList);
    void initWheelsElements(ComboBox<String> wheelsList);
}

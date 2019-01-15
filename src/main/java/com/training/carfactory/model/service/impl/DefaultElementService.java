package com.training.carfactory.model.service.impl;

import com.sun.javafx.collections.ObservableListWrapper;
import com.training.carfactory.model.entity.Body;
import com.training.carfactory.model.entity.Engine;
import com.training.carfactory.model.entity.Wheels;
import com.training.carfactory.model.service.BodyService;
import com.training.carfactory.model.service.ElementService;
import com.training.carfactory.model.service.EngineService;
import com.training.carfactory.model.service.WheelsService;
import javafx.scene.control.ComboBox;

import java.util.stream.Collectors;

public class DefaultElementService implements ElementService {

    private BodyService bodyService;
    private EngineService engineService;
    private WheelsService wheelsService;

    public DefaultElementService(BodyService bodyService, EngineService engineService, WheelsService wheelsService) {
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.wheelsService = wheelsService;}

    @Override
    public void initBodyElements(ComboBox<String> bodiesList) {
        initBodies(bodiesList);
    }

    @Override
    public void initEngineElements(ComboBox<String> enginesList) {initEngines(enginesList); }

    @Override
    public void initWheelsElements(ComboBox<String> wheelsList) {initWheelsList(wheelsList);}

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

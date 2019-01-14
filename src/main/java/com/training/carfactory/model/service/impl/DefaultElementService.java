package com.training.carfactory.model.service.impl;

import com.sun.javafx.collections.ObservableListWrapper;
import com.training.carfactory.model.entity.Body;
import com.training.carfactory.model.service.BodyService;
import com.training.carfactory.model.service.ElementService;
import javafx.scene.control.ComboBox;

import java.util.stream.Collectors;

public class DefaultElementService implements ElementService {

    private BodyService bodyService;

    public DefaultElementService(BodyService bodyService) {
        this.bodyService = bodyService;
    }

    @Override
    public void initElements(ComboBox<String> bodiesList) {
        initBodies(bodiesList);
    }

    private void initBodies(ComboBox<String> bodiesList){
        bodiesList.setItems(new ObservableListWrapper<>(bodyService.getAll().
                stream().map(Body::getName).collect(Collectors.toList())));
    }
}

package com.training.carfactory.model.service;

import javafx.scene.Node;

public interface PageService {

    void switchToPage(Node target);

    void setCurrentPage(Node page);
}

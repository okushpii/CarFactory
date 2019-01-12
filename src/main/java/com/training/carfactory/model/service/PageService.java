package com.training.carfactory.model.service;

import javafx.scene.Node;

public interface PageService {

    void initializePages(Node...nodes);

    Node getCurrentPage();

    Node getNextPage();

    Node getPreviousPage();

}

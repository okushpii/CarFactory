package com.training.carfactory.model.service.context;

import javafx.scene.Node;

import java.util.LinkedList;

public class PageContext {

    private Node currentPage;

    public Node getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Node currentPage) {
        this.currentPage = currentPage;
    }
}

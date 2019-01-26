package com.training.carfactory.controller.context;

import javafx.scene.Node;

public class PageContext {

    private Node currentPage;

    public Node getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Node currentPage) {
        this.currentPage = currentPage;
    }
}

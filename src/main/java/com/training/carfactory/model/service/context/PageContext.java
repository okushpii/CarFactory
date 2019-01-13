package com.training.carfactory.model.service.context;

import javafx.scene.Node;

import java.util.LinkedList;

public class PageContext {

    private static PageContext instance;
    private Node currentPage;

    private PageContext(){
    }

    public static PageContext getInstance(){
        if (instance == null){
            instance = new PageContext();
        }
        return instance;
    }
    public Node getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Node currentPage) {
        this.currentPage = currentPage;
    }
}

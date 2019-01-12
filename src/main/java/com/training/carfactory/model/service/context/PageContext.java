package com.training.carfactory.model.service.context;

import javafx.scene.Node;

import java.util.LinkedList;

public class PageContext {

    private static PageContext instance;
    private int currentNodeIndex;
    private LinkedList<Node> nodes;

    private PageContext(){
    }

    public static PageContext getInstance(){
        if (instance == null){
            instance = new PageContext();
        }
        return instance;
    }

    public int getCurrentNodeIndex() {
        return currentNodeIndex;
    }

    public void setCurrentNodeIndex(int currentNodeIndex) {
        this.currentNodeIndex = currentNodeIndex;
    }

    public LinkedList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(LinkedList<Node> nodes) {
        this.nodes = nodes;
    }
}

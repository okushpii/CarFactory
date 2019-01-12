package com.training.carfactory.model.service.impl;

import com.training.carfactory.model.service.context.PageContext;
import com.training.carfactory.model.service.PageService;
import javafx.scene.Node;

import java.util.Arrays;
import java.util.LinkedList;

public class DefaultPageService implements PageService {

    private PageContext pageContext = PageContext.getInstance();

    @Override
    public void initializePages(Node... nodes) {
        pageContext.setNodes(new LinkedList<>(Arrays.asList(nodes)));
    }

    @Override
    public Node getCurrentPage() {
        return pageContext.getNodes().
                get(pageContext.getCurrentNodeIndex());
    }

    @Override
    public Node getNextPage() {
        int index = getIndex();
        pageContext.setCurrentNodeIndex(index);
        return pageContext.getNodes().get(index);
    }

    @Override
    public Node getPreviousPage() {
        int index = pageContext.getCurrentNodeIndex() - 1;
        pageContext.setCurrentNodeIndex(index);
        return pageContext.getNodes().get(index);
    }

    private int getIndex() {
        int index = 0;
        if (pageContext.getCurrentNodeIndex() + 1 < pageContext.getNodes().size()){
            index = pageContext.getCurrentNodeIndex() + 1;
        }
        return index;
    }
}

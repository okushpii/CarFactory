package com.training.carfactory.model.service.impl;

import com.training.carfactory.model.service.context.PageContext;
import com.training.carfactory.model.service.PageService;
import javafx.scene.Node;

import java.util.Arrays;
import java.util.LinkedList;

public class DefaultPageService implements PageService {

    private PageContext pageContext;

    public DefaultPageService(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public Node getCurrentPage() {
        return pageContext.getCurrentPage();
    }

    @Override
    public void setCurrentPage(Node node) {
        pageContext.setCurrentPage(node);
    }
}

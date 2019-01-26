package com.training.carfactory.model.service.impl;

import com.training.carfactory.model.service.PageService;
import com.training.carfactory.controller.context.PageContext;
import javafx.scene.Node;

import java.util.NoSuchElementException;

public class DefaultPageService implements PageService {

    private PageContext pageContext;

    public DefaultPageService(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public void switchToPage(Node target){
        checkCurrentPagePresence();
        pageContext.getCurrentPage().setVisible(false);
        target.setVisible(true);
        pageContext.setCurrentPage(target);
    }

    @Override
    public void setCurrentPage(Node page) {
        pageContext.setCurrentPage(page);
    }

    private void checkCurrentPagePresence() {
        if (pageContext.getCurrentPage() == null){
            throw new NoSuchElementException("Current node was not initialized");
        }
    }
}

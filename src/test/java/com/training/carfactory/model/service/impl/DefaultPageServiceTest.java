package com.training.carfactory.model.service.impl;

import com.training.carfactory.model.service.context.PageContext;
import javafx.scene.layout.AnchorPane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultPageServiceTest {

    private DefaultPageService defaultPageService;

    private PageContext pageContext;

    @Before
    public void setUp(){
        pageContext = new PageContext();
        defaultPageService = new DefaultPageService(pageContext);
    }

    @Test
    public void shouldGetCurrentPage(){
        AnchorPane currentPage = new AnchorPane();
        pageContext.setCurrentPage(currentPage);
        assertEquals(currentPage, defaultPageService.getCurrentPage());
    }

    @Test
    public void shouldSetCurrentPage(){
        AnchorPane currentPage = new AnchorPane();
        defaultPageService.setCurrentPage(currentPage);
        assertEquals(currentPage, pageContext.getCurrentPage());
    }
}
package com.training.carfactory.model.service.impl;

import com.training.carfactory.controller.context.PageContext;
import javafx.scene.layout.AnchorPane;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

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
    public void shouldSwitchToPage(){
        AnchorPane currentPage = new AnchorPane();
        AnchorPane target = new AnchorPane();
        defaultPageService.setCurrentPage(currentPage);
        defaultPageService.switchToPage(target);
        assertEquals(target, pageContext.getCurrentPage());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldSwitchPageWhenCurrentPageIsAbsent(){
        AnchorPane target = new AnchorPane();
        defaultPageService.switchToPage(target);
    }

    @Test
    public void shouldSetCurrentPage(){
        AnchorPane currentPage = new AnchorPane();
        defaultPageService.setCurrentPage(currentPage);
        assertEquals(currentPage, pageContext.getCurrentPage());
    }
}